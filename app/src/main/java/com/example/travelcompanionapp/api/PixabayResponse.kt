package com.example.travelcompanionapp.api

data class PixabayResponse(
    val hits: List<Photo>
)

data class Photo(
    val id: Int,
    val webformatURL: String,
    val tags: String
)

