package com.example.healthylivingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResepViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is resep Fragment"
    }
    val text: LiveData<String> = _text
}