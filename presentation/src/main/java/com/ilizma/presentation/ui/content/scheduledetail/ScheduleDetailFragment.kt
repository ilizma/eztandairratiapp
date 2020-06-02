package com.ilizma.presentation.ui.content.scheduledetail

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import dagger.Lazy
import javax.inject.Inject

class ScheduleDetailFragment : BaseFragment() {

    @Inject
    override lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var scheduleDetailViewModel: ScheduleDetailViewModel

    override var fragmentLayout = R.layout.fragment_schedule_detail


}