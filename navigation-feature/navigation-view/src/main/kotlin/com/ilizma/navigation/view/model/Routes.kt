package com.ilizma.navigation.view.model

sealed class Routes(
    val route: String,
) {

    data object Main : Routes(
        route = "main",
    )

    data object ScheduleDetail : Routes(
        route = "scheduleDetail?id={id}&name={name}",
    ) {
        fun createRoute(
            id: Int,
            name: String,
        ): String = "scheduleDetail?id=$id&name=$name"
    }
}