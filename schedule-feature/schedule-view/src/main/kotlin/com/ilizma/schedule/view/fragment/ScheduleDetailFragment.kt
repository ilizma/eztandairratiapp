package com.ilizma.schedule.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ilizma.schedule.view.bind.ScheduleDetailBinder
import com.ilizma.schedule.view.databinding.ScheduleDetailFragmentBinding
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.R
import com.ilizma.view.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleDetailFragment : Fragment(R.layout.schedule_detail_fragment) {

    @Inject
    internal lateinit var binder: ScheduleDetailBinder

    @Inject
    internal lateinit var router: ScheduleDetailRouter

    private val binding by viewBinding(ScheduleDetailFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        router.init()
    }

    private fun setupView() {
        binder.bind(binding)
    }

}