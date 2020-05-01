package com.ilizma.presentation.ui.content.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class GalleryViewModel @Inject constructor() : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}