package com.ilizma.schedule.view.bind

import androidx.lifecycle.LifecycleOwner
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.adapter.DaysAdapter
import com.ilizma.schedule.view.adapter.factory.DaysAdapterFactory
import com.ilizma.schedule.view.databinding.ScheduleScreenFragmentBinding

class ScheduleScreenBinderImp(
    viewModelLazy: Lazy<ScheduleScreenViewModel>,
    private val lifecycleOwner: () -> LifecycleOwner,
    adapterFactory: DaysAdapterFactory,
) : ScheduleScreenBinder {

    private val viewModel: ScheduleScreenViewModel by viewModelLazy
    private val adapter: DaysAdapter by lazy { adapterFactory.create() }
    private lateinit var binding: ScheduleScreenFragmentBinding

    override fun bind(binding: ScheduleScreenFragmentBinding) {
        this.binding = binding
        initRecyclerView()
        setupObservers()
    }

    private fun initRecyclerView() {
        binding.scheduleScreenFragmentRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.days.observe(
            lifecycleOwner(),
        ) { onDays(it) }
    }

    private fun onDays(
        days: Days,
    ) {
        adapter.submitList(days.dayList)
    }

}