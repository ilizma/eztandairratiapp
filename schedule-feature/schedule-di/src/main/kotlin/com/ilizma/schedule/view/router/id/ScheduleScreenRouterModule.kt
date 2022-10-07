package com.ilizma.schedule.view.router.id

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.flow.mapper.DayMapper
import com.ilizma.schedule.flow.navigator.ScheduleBackCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.router.ScheduleScreenRouterImp
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenRouterModule {

    @Provides
    fun provideScheduleScreenRouter(
        fragment: Fragment,
        scheduleBackCloseNavigator: ScheduleBackCloseNavigator,
        scheduleDetailNavigator: ScheduleDetailNavigator,
        @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleScreenRouter = ScheduleScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = fragment.requireActivity().onBackPressedDispatcher,
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        mapper = DayMapper(),
        scheduleBackCloseNavigator = scheduleBackCloseNavigator,
        scheduleDetailNavigator = scheduleDetailNavigator,
    )

}