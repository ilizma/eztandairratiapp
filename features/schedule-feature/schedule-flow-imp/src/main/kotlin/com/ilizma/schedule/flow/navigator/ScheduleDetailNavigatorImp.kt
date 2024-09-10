package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel

class ScheduleDetailNavigatorImp(
    viewModelLazy: Lazy<ScheduleScreenDetailViewModel>,
) : ScheduleDetailNavigator {

    private val viewModel: ScheduleScreenDetailViewModel by viewModelLazy

    override fun navigate(
        navController: NavHostController,
        id: Int,
        name: String,
    ) {
        ScheduleDetail(
            id = id,
            name = name,
        ).let { navController.navigate(it) }
    }

}