package com.ilizma.schedule.flow.navigator

import cafe.adriel.voyager.navigator.Navigator
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.compose.SCHEDULE_ID
import com.ilizma.schedule.view.compose.SCHEDULE_NAME
import com.ilizma.schedule.view.compose.ScheduleDetailScreen
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class ScheduleDetailNavigatorImp(
    viewModelLazy: Lazy<ScheduleScreenDetailViewModel>,
) : ScheduleDetailNavigator {

    private val viewModel: ScheduleScreenDetailViewModel by viewModelLazy

    override fun navigate(
        navigator: Navigator,
        id: Int,
        name: String,
    ) {
        val settings = Settings()
        settings[SCHEDULE_ID] = id
        settings[SCHEDULE_NAME] = name
        ScheduleDetailScreen(viewModel = viewModel)
            .also {
                viewModel.getTitle()
                viewModel.getSchedule()
            }
            .let { navigator.push(it) }
    }

}