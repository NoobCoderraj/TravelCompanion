// api/ApiService.kt
package com.example.travelcompanionapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("?key=44195578-d6c093cb087d7cafc040eb5a6")
    suspend fun getTouristPlaces(@Query("q") query: String = "tourist destinations"): PixabayResponse
}