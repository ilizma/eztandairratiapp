package com.ilizma.schedule.view.adapter.util.di

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.adapter.util.DayDiffUtilImp
import com.ilizma.view.adapter.util.ItemDiffUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DayDiffUtilModule {

    @Provides
    fun provideDayDiffUtil(
    ): ItemDiffUtil<Day> = DayDiffUtilImp()

}