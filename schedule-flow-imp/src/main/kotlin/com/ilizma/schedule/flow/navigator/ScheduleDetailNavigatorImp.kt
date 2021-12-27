package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavController
import com.ilizma.schedule.flow.model.Day

class ScheduleDetailNavigatorImp(
    private val navController: NavController,//findNavController()
) : ScheduleDetailNavigator {

    override fun navigate(day: Day) {
        ScheduleScreenFragmentDirections.toScheduleDetailFragment(day)
            .let { navController.navigate(it) }
    }

}