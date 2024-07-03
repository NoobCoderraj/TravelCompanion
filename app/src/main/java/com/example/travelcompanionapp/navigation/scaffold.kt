package com.example.travelcompanionapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.travelcompanionapp.components.BottomNavigationBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithBottomNav(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        content()
    }
}
