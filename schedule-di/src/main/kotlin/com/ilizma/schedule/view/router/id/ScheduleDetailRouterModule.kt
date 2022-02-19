package com.ilizma.schedule.view.router.id

import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.schedule.flow.navigator.ScheduleDetailBackCloseNavigator
import com.ilizma.schedule.flow.router.ScheduleDetailRouterImp
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailRouterModule {

    @Provides
    fun provideScheduleDetailRouter(
        fragment: Fragment,
        navigator: ScheduleDetailBackCloseNavigator,
    ): ScheduleDetailRouter = ScheduleDetailRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = OnBackPressedDispatcher(),
        viewModelLazy = fragment.viewModels(),
        navigator = navigator,
    )

}