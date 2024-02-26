package com.ilizma.schedule.view.router.id

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.flow.router.ScheduleDetailRouterImp
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailRouterModule {

    @Provides
    fun provideScheduleDetailRouter(
        activity: Activity,
        closeNavigator: ScheduleDetailCloseNavigator,
        @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleDetailRouter = ScheduleDetailRouterImp(
        viewModelLazy = (activity as ComponentActivity).viewModels { viewModelProviderFactory },
        lifecycleCoroutineScope = activity.lifecycleScope,
        closeNavigator = closeNavigator,
    )

}