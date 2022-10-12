package com.ilizma.menu.view.router.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.menu.flow.navigator.*
import com.ilizma.menu.flow.router.MenuScreenRouterImp
import com.ilizma.menu.presentation.viewmodel.factory.di.MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.menu.view.router.MenuScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object MenuScreenRouterModule {

    @Provides
    fun provideMenuScreenRouter(
        fragment: Fragment,
        instagramNavigator: InstagramNavigator,
        twitterNavigator: TwitterNavigator,
        facebookNavigator: FacebookNavigator,
        phoneNavigator: PhoneNavigator,
        webNavigator: WebNavigator,
        menuBackCloseNavigator: MenuBackCloseNavigator,
        @Named(MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): MenuScreenRouter = MenuScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = fragment.requireActivity().onBackPressedDispatcher,
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        instagramNavigator = instagramNavigator,
        twitterNavigator = twitterNavigator,
        facebookNavigator = facebookNavigator,
        phoneNavigator = phoneNavigator,
        webNavigator = webNavigator,
        menuBackCloseNavigator = menuBackCloseNavigator,
    )

}