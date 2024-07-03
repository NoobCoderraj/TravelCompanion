package com.example.travelcompanionapp.screens

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.travelcompanionapp.R
import com.example.travelcompanionapp.navigation.Routes

@Composable 

fun Splash(navController: NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter) // Align the column to the top center of the Box
        ) {
            Image(
                painter = painterResource(id = R.drawable.spal),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp, bottom = 16.dp)
            )
          
            Text(text = "Plan Your Next Trip",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                )

            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Have full control of your next trip",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "within your budget and timeline",
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
            }

            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.primaryColor),
                    contentColor = colorResource(id = R.color.white)
                ),
                onClick = { navController.navigate(Routes.Login.routes)},
            ) {
                Text(text = "Explore the world")
            }
        }

        Image(
            painter = painterResource(id = R.drawable.bottomsplash),
            contentDescription = "Circular Image",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .size(300.dp)
        )
    }

}