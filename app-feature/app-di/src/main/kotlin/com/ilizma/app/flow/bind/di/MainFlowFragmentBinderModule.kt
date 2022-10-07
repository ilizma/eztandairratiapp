package com.ilizma.app.flow.bind.di

import androidx.fragment.app.Fragment
import com.ilizma.app.flow.bind.MainFlowFragmentBinder
import com.ilizma.app.flow.bind.MainFlowFragmentBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MainFlowFragmentBinderModule {

    @Provides
    fun provideMainFlowFragmentBinder(
        fragment: Fragment,
    ): MainFlowFragmentBinder = MainFlowFragmentBinderImp { fragment.childFragmentManager }

}