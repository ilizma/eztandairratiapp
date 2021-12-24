package com.ilizma.eztandairratiapp.di.module.presentation

import com.ilizma.eztandairratiapp.di.scope.PerView
import com.ilizma.presentation.ui.content.menu.MenuFragment
import com.ilizma.presentation.ui.content.schedule.ScheduleFragment
import com.ilizma.presentation.ui.content.scheduledetail.ScheduleDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun radio(): RadioFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun schedule(): ScheduleFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun scheduleDetail(): ScheduleDetailFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun menu(): MenuFragment

}
