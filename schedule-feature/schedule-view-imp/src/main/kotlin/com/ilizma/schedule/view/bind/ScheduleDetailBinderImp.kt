package com.ilizma.schedule.view.bind

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.ilizma.resources.R
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailViewModel
import com.ilizma.schedule.view.databinding.ScheduleDetailFragmentBinding
import com.ilizma.view.adapter.Adapter
import com.ilizma.view.adapter.factory.AdapterFactory
import com.ilizma.view.extensions.snackbar

class ScheduleDetailBinderImp(
    viewModelLazy: Lazy<ScheduleDetailViewModel>,
    private val lifecycleOwner: () -> LifecycleOwner,
    adapterFactory: AdapterFactory<ProgramType>,
) : ScheduleDetailBinder {

    private val viewModel: ScheduleDetailViewModel by viewModelLazy
    private val adapter: Adapter<ProgramType> by lazy { adapterFactory.create() }
    private lateinit var binding: ScheduleDetailFragmentBinding

    override fun bind(binding: ScheduleDetailFragmentBinding) {
        this.binding = binding
        setupToolbar(binding)
        initRecyclerView()
        setupObservers()
    }

    private fun setupToolbar(
        binding: ScheduleDetailFragmentBinding
    ) {
        binding.menuScreenT.setNavigationOnClickListener { viewModel.onBack() }
    }

    private fun initRecyclerView() {
        binding.scheduleDetailFragmentRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.dayName.observe(
            lifecycleOwner(),
        ) { onDayName(it) }

        viewModel.scheduleState.observe(
            lifecycleOwner(),
        ) { onSchedule(it) }

        viewModel.error.observe(
            lifecycleOwner(),
        ) { onError(it) }
    }

    private fun onDayName(
        dayName: String,
    ) {
        binding.menuScreenT.title = dayName
    }

    private fun onSchedule(
        scheduleState: ScheduleState,
    ) {
        binding.scheduleDetailFragmentRv.isVisible = scheduleState.list.isNotEmpty()
        binding.scheduleDetailFragmentIvEmpty.isVisible = scheduleState.list.isEmpty()
        binding.scheduleDetailFragmentTvEmpty.isVisible = scheduleState.list.isEmpty()
        adapter.submitList(scheduleState.list)
    }

    private fun onError(
        errorMessage: String,
    ) {
        showError(binding.root, errorMessage)

    }

    private fun showError(
        view: View,
        errorMessage: String
    ) {
        view.context.getString(R.string.retry)
            .let { view.snackbar(errorMessage, it) { viewModel.getSchedule() } }
    }

}