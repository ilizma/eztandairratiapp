package com.ilizma.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ilizma.app.view.R
import com.ilizma.review.framework.PlayReviewFramework
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject
    internal lateinit var reviewFramework: PlayReviewFramework

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewFramework.request()
    }

}
