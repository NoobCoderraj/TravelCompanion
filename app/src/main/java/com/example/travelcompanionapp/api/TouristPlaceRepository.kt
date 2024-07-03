package com.example.travelcompanionapp.api

class TouristPlaceRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getTouristPlaces(): List<Photo> {
        return apiService.getTouristPlaces().hits
    }
}
