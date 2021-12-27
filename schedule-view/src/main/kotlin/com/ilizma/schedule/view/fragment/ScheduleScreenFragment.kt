package com.ilizma.schedule.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.schedule.view.bind.ScheduleScreenBinder
import com.ilizma.schedule.view.databinding.ScheduleScreenFragmentBinding
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import com.ilizma.schedule.view.R
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleScreenFragment : Fragment(R.layout.schedule_screen_fragment) {

    @Inject
    internal lateinit var binder: ScheduleScreenBinder

    @Inject
    internal lateinit var router: ScheduleScreenRouter

    private val binding by viewBinding(ScheduleScreenFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        router.init()
    }

    private fun setupView() {
        binder.bind(binding)
    }

}