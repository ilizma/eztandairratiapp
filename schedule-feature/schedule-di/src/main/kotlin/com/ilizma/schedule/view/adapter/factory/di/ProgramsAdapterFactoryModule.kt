package com.ilizma.schedule.view.adapter.factory.di

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.adapter.factory.ProgramsAdapterFactoryImp
import com.ilizma.schedule.view.bind.factory.ProgramBinderFactory
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory
import com.ilizma.view.adapter.util.ItemDiffUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ProgramsAdapterFactoryModule {

    @Provides
    fun provideProgramsAdapterFactory(
        binderFactory: ProgramBinderFactory,
        diffUtil: ItemDiffUtil<ProgramType>,
        viewHolderFactory: ProgramViewHolderFactory,
    ): ProgramsAdapterFactoryImp = ProgramsAdapterFactoryImp(
        binderFactory = binderFactory,
        diffUtil = diffUtil,
        viewHolderFactory = viewHolderFactory,
    )

}