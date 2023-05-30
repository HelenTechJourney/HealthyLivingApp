package com.example.healthylivingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JelajahiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is jelajahi Fragment"
    }
    val text: LiveData<String> = _text
}