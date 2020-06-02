package com.ilizma.presentation.ui.content.schedule

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment
import dagger.Lazy
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {

    @Inject
    override lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var scheduleViewModel: ScheduleViewModel

    override var fragmentLayout = R.layout.fragment_schedule


}