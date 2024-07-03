package com.example.travelcompanionapp.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelcompanionapp.Home


import com.example.travelcompanionapp.screens.Login
import com.example.travelcompanionapp.screens.Planning
import com.example.travelcompanionapp.screens.Plans
import com.example.travelcompanionapp.screens.Profile
import com.example.travelcompanionapp.screens.Registration
import com.example.travelcompanionapp.screens.Splash



@SuppressLint("ComposableDestinationInComposeScope")
@Composable

fun navGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.Home.routes){

        composable(Routes.Splash.routes){
            Splash(navController)
        }

        composable(Routes.Login.routes){
            Login(navController)
        }

        composable(Routes.Register.routes){
            Registration(navController)
        }


        composable(Routes.Home.routes) {
            ScaffoldWithBottomNav(navController) {
                Home(navController)
            }
        }
        composable(Routes.Profile.routes) {
            ScaffoldWithBottomNav(navController) {
                Profile(navController)
            }
        }
        composable(Routes.Plans.routes) {
            ScaffoldWithBottomNav(navController) {
                Plans(navController)
            }



        }

    }
}