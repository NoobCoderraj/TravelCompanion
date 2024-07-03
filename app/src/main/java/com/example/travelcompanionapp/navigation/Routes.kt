package com.example.travelcompanionapp.navigation


sealed class Routes (val routes:String){

    object Splash:Routes("splash")
    object Login:Routes("login")
    object Register:Routes("register")
    object Home:Routes("home")
    object Profile:Routes("profile")
    object Plans:Routes("Plans")
    object Planning:Routes("Planning")

}