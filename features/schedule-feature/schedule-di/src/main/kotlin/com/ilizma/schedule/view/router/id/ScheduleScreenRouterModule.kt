package com.ilizma.schedule.view.router.id

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ilizma.schedule.flow.navigator.ScheduleBackNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.router.ScheduleScreenRouterImp
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object ScheduleScreenRouterModule {

    @Provides
    fun provideScheduleScreenRouter(
        activity: Activity,
        scheduleBackNavigator: ScheduleBackNavigator,
        scheduleDetailNavigator: ScheduleDetailNavigator,
        @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleScreenRouter = ScheduleScreenRouterImp(
        viewModelLazy = (activity as ComponentActivity).viewModels { viewModelProviderFactory },
        lifecycleCoroutineScope = activity.lifecycleScope,
        scheduleBackNavigator = scheduleBackNavigator,
        scheduleDetailNavigator = scheduleDetailNavigator,
    )

}