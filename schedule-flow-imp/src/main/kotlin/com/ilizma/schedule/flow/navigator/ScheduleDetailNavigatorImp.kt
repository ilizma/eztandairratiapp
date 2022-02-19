package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavController
import com.ilizma.schedule.flow.model.Day
import com.ilizma.schedule.view.fragment.ScheduleScreenFragmentDirections

class ScheduleDetailNavigatorImp(
    private val navController: NavController,
) : ScheduleDetailNavigator {

    override fun navigate(day: Day) {
        ScheduleScreenFragmentDirections.toScheduleDetailFragment(day)
            .let { navController.navigate(it) }
    }

}