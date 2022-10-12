package com.ilizma.schedule.view.adapter.util.di

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.adapter.util.ProgramDiffUtilImp
import com.ilizma.view.adapter.util.ItemDiffUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ProgramDiffUtilModule {

    @Provides
    fun provideProgramDiffUtil(
    ): ItemDiffUtil<ProgramType> = ProgramDiffUtilImp()

}