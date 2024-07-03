package com.example.travelcompanionapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.travelcompanionapp.R
import com.example.travelcompanionapp.auth.AuthViewModel
import com.example.travelcompanionapp.navigation.Routes

@Composable

fun Registration(navController: NavHostController){

    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }

    val lightOrange = Color(0xFFE49A2E) // Define your light orange color here
    val orange = Color(0xFFFFA726) // Define your orange color here

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)), // Background color
        verticalArrangement = Arrangement.Top
    ) {
        // Top half with image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(lightOrange), // Set background color here
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.register), // replace with your image resource
                contentDescription = "Login page",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            )
        }

        // Bottom half with email, password, button and text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(lightOrange)

        // Continue background color here
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // Set card color to white
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Your Journey Begins Here",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = surname,
                        onValueChange = { surname = it },
                        label = { Text("Surname") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.signUp(email, password, name, surname) { success ->
                                if (success) {
                                    navController.navigate(Routes.Home.routes)
                                } else {

                                }
                            }

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE49A2E)), // Set button color to orange
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Register")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick ={navController.navigate(Routes.Login.routes)} ,


                            ) {
                            Text(text = "Login",
                                color = Color(0xFFE49A2E),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(end = 4.dp)
                                )
                        }
//                        CEl(
//                            text = "Login",
//                            color = Color(0xFFE49A2E),
//                            fontSize = 16.sp,
//                            modifier = Modifier.padding(end = 4.dp) // Adjust padding here
//                        )

                        Icon(
                            painter = painterResource(id = R.drawable.rightarrow_foreground),
                            contentDescription = "Right Arrow",
                            tint = Color.Black, // Tint the icon to match the text color
                            modifier = Modifier.size(24.dp) // Adjust the size of the icon
                        )
                    }
                }
            }
        }
    }
}