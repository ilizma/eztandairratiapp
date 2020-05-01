package com.ilizma.presentation.ui.content.slideshow

import android.os.Bundle
import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.observe
import com.ilizma.presentation.extensions.viewModel
import com.ilizma.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : BaseFragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override var fragmentLayout = R.layout.fragment_slideshow

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideshowViewModel = viewModel(viewModelFactory.get()) {
            observe(text) {
                textSlideshow.text = it
            }
        }
    }

}
