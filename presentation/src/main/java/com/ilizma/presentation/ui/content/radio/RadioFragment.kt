package com.ilizma.presentation.ui.content.radio

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity
import dagger.Lazy
import javax.inject.Inject

class RadioFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var radioViewModel: RadioViewModel

    override var fragmentLayout = R.layout.fragment_radio

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_menu)
    }


}