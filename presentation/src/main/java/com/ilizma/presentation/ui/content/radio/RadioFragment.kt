package com.ilizma.presentation.ui.content.radio

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import dagger.Lazy
import javax.inject.Inject

class RadioFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var radioViewModel: RadioViewModel

    override var fragmentLayout = R.layout.fragment_radio


}