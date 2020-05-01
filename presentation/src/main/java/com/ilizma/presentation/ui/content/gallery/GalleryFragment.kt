package com.ilizma.presentation.ui.content.gallery

import android.os.Bundle
import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.observe
import com.ilizma.presentation.extensions.viewModel
import com.ilizma.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : BaseFragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override var fragmentLayout = R.layout.fragment_gallery

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel = viewModel(viewModelFactory.get()) {
            observe(text) {
                textGallery.text = it
            }
        }
    }

}
