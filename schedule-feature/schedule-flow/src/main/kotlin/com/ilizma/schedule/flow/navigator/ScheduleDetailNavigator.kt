package com.ilizma.schedule.flow.navigator

import cafe.adriel.voyager.navigator.Navigator

interface ScheduleDetailNavigator {

    fun navigate(
        navigator: Navigator,
        id: Int,
        name: String
    )

}