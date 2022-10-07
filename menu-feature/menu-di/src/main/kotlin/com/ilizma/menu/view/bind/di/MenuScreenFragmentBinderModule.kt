package com.ilizma.menu.view.bind.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.menu.presentation.viewmodel.factory.di.MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.menu.view.bind.MenuScreenFragmentBinder
import com.ilizma.menu.view.bind.MenuScreenFragmentBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object MenuScreenFragmentBinderModule {

    @Provides
    fun provideMenuScreenFragmentBinder(
        fragment: Fragment,
        @Named(MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): MenuScreenFragmentBinder = MenuScreenFragmentBinderImp(
        fragment.viewModels { viewModelProviderFactory }
    )

}