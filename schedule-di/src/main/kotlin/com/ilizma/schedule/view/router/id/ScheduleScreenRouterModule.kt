package com.ilizma.schedule.view.router.id

import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.schedule.flow.mapper.DayMapper
import com.ilizma.schedule.flow.navigator.ScheduleBackCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.router.ScheduleScreenRouterImp
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenRouterModule {

    @Provides
    fun provideScheduleScreenRouter(
        fragment: Fragment,
        scheduleBackCloseNavigator: ScheduleBackCloseNavigator,
        scheduleDetailNavigator: ScheduleDetailNavigator,
    ): ScheduleScreenRouter = ScheduleScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = OnBackPressedDispatcher(),
        viewModelLazy = fragment.viewModels(),
        mapper = DayMapper(),
        scheduleBackCloseNavigator = scheduleBackCloseNavigator,
        scheduleDetailNavigator = scheduleDetailNavigator,
    )

}