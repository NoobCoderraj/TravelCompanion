package com.example.travelcompanionapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlannedImageViewModel : ViewModel() {
    private val _plannedImages = MutableLiveData<List<PlannedImage>>(emptyList())
    val plannedImages: LiveData<List<PlannedImage>> get() = _plannedImages

    fun addImage(plannedImage: PlannedImage) {
        _plannedImages.value = _plannedImages.value?.plus(plannedImage)
    }
}
