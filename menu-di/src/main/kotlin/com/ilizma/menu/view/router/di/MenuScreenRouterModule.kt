package com.ilizma.menu.view.router.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ilizma.menu.flow.navigator.*
import com.ilizma.menu.flow.router.MenuScreenRouterImp
import com.ilizma.menu.view.router.MenuScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object MenuScreenRouterModule {

    @Provides
    fun provideMenuScreenRouter(
        fragment: Fragment,
        twitterNavigator: TwitterNavigator,
        facebookNavigator: FacebookNavigator,
        whatsappNavigator: WhatsappNavigator,
        phoneNavigator: PhoneNavigator,
        webNavigator: WebNavigator,
    ): MenuScreenRouter = MenuScreenRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        viewModelLazy = fragment.viewModels(),
        twitterNavigator = twitterNavigator,
        facebookNavigator = facebookNavigator,
        whatsappNavigator = whatsappNavigator,
        phoneNavigator = phoneNavigator,
        webNavigator = webNavigator,
    )

}