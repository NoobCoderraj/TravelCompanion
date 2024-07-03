package com.example.travelcompanionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.travelcompanionapp.navigation.navGraph

import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContent {
val navController = rememberNavController()
            navGraph(navController = navController)
        }
    }
}

