// api/TouristPlaceViewModel.kt
package com.example.travelcompanionapp.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TouristPlaceViewModel : ViewModel() {
    private val repository = TouristPlaceRepository()

    private val _touristPlaces = MutableLiveData<List<Photo>>()
    val touristPlaces: LiveData<List<Photo>> get() = _touristPlaces

    init {
        fetchTouristPlaces()
    }

    private fun fetchTouristPlaces() {
        viewModelScope.launch {
            val places = repository.getTouristPlaces()
            _touristPlaces.value = places
        }
    }
}
