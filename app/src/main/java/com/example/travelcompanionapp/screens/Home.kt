package com.example.travelcompanionapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.travelcompanionapp.api.Photo
import com.example.travelcompanionapp.api.TouristPlaceViewModel
import com.example.travelcompanionapp.navigation.ScaffoldWithBottomNav
import com.example.travelcompanionapp.screens.Planning
import com.example.travelcompanionapp.screens.SelectedImage

@Composable
fun Home(navController: NavHostController) {
    ScaffoldWithBottomNav(navController) {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    val viewModel: TouristPlaceViewModel = viewModel()
    val touristPlaces by viewModel.touristPlaces.observeAsState(emptyList())

    // State to hold selected image
    var selectedImage by remember { mutableStateOf<SelectedImage?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        SearchBarWithLogo()

        Spacer(modifier = Modifier.height(16.dp))

        // "CURATE YOUR HOLIDAY" with half orange, half black
        Image(
            painter = painterResource(id = R.drawable.topimage),
            contentDescription = "Top image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "to trending destinations",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalImageScroller(touristPlaces)

        Spacer(modifier = Modifier.height(16.dp))

        // "Journeys of the week" with half orange, half black
        Image(
            painter = painterResource(id = R.drawable.week),
            contentDescription = "Journey",
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))
        ScrollableImageCard(touristPlaces) { selectedPhoto ->
            selectedImage = SelectedImage(selectedPhoto.webformatURL, selectedPhoto.tags)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CardGrid(touristPlaces)

        Spacer(modifier = Modifier.height(16.dp))

        // Navigate to Planning.kt when an image is selected
        if (selectedImage != null) {
            Planning(selectedImage!!) {
                selectedImage = null  // Reset selected image after handling
            }
        }
    }
}

@Composable
fun SearchBarWithLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_logo),
            contentDescription = "Home Image",
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        val searchQuery = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            BasicTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.Black), // Set text color
                singleLine = true, // Ensure single line input
                decorationBox = { innerTextField ->
                    if (searchQuery.value.isEmpty()) {
                        Box(contentAlignment = Alignment.CenterStart) {
                            Text(
                                text = "Find your dreamland",
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 16.dp) // Adjust padding as needed
                            )
                            innerTextField()
                        }
                    } else {
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
fun HorizontalImageScroller(touristPlaces: List<Photo>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        touristPlaces.forEach { place ->
            TouristPlaceCard(place)
        }
    }
}

@Composable
fun TouristPlaceCard(place: Photo) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(data = place.webformatURL),
            contentDescription = place.tags,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Text(
            text = place.tags.split(",").firstOrNull() ?: "Unknown place",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CardGrid(touristPlaces: List<Photo>) {
    var selectedCardIndex by remember { mutableStateOf(-1) }

    if (selectedCardIndex != -1) {
        CardDetailsDialog(selectedCardIndex) {
            selectedCardIndex = -1
        }
    }

    // Ensure there are enough items to display
    val itemCount = minOf(touristPlaces.size, 6)

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            repeat(itemCount.coerceAtMost(3)) { index ->
                JourneyCard(touristPlaces[index]) { selectedCardIndex = index }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (itemCount > 3) {
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(itemCount - 3) { index ->
                    JourneyCard(touristPlaces[index + 3]) { selectedCardIndex = index + 3 }
                }
            }
        }
    }
}

@Composable
fun JourneyCard(photo: Photo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clickable(onClick = onClick)
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(data = photo.webformatURL),
                contentDescription = "Card Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = photo.tags.split(",").firstOrNull() ?: "Unknown place",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ScrollableImageCard(touristPlaces: List<Photo>, onPhotoSelected: (Photo) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Row {
            touristPlaces.forEach { place ->
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                        .padding(8.dp)
                        .clickable { onPhotoSelected(place) }
                ) {
                    Image(
                        painter = rememberImagePainter(data = place.webformatURL),
                        contentDescription = place.tags,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Text(
                        text = place.tags.split(",").firstOrNull() ?: "Unknown place",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .background(Color(0x80000000))
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun CardDetailsDialog(index: Int, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Text(text = "Details for Item $index", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onDismiss) {
            Text("Close")
        }
    }
}
