package com.ilizma.presentation.ui.content.radio

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.presentation.ui.base.BaseViewModel
import dagger.Lazy
import javax.inject.Inject

class RadioViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    @Inject
    override lateinit var chuckerCollector: Lazy<ChuckerCollector>

    private val _ldRoverData = MutableLiveData<String>()
    val ldRoverData: LiveData<String> = _ldRoverData

    fun example(example: String) {

    }

}