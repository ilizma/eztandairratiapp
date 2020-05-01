package com.ilizma.presentation.ui.content.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}