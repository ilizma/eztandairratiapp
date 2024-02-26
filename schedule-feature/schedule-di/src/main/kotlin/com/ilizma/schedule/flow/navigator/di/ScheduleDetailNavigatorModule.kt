package com.ilizma.schedule.flow.navigator.di

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigatorImp
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailNavigatorModule {

    @Provides
    fun provideScheduleDetailNavigator(
        activity: Activity,
        @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleDetailNavigator = ScheduleDetailNavigatorImp(
        viewModelLazy = (activity as ComponentActivity).viewModels { viewModelProviderFactory },
    )

}