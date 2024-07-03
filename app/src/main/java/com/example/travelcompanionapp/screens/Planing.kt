package com.example.travelcompanionapp.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

// Data model for storing selected image data
data class SelectedImage(val imageUrl: String, val tags: String)

@Composable
fun Planning(selectedImage: SelectedImage, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Image Details", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = rememberImagePainter(data = selectedImage.imageUrl),
            contentDescription = selectedImage.tags,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
