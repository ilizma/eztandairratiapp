package com.ilizma.menu.view.router.di

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.menu.flow.navigator.*
import com.ilizma.menu.flow.router.MenuScreenRouterImp
import com.ilizma.menu.presentation.viewmodel.factory.di.MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.menu.view.router.MenuScreenRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object MenuScreenRouterModule {

    @Provides
    fun provideMenuScreenRouter(
        activity: Activity,
        instagramNavigator: InstagramNavigator,
        twitterNavigator: TwitterNavigator,
        facebookNavigator: FacebookNavigator,
        phoneNavigator: PhoneNavigator,
        webNavigator: WebNavigator,
        menuBackNavigator: MenuBackNavigator,
        @Named(MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): MenuScreenRouter = MenuScreenRouterImp(
        viewModelLazy = (activity as ComponentActivity).viewModels { viewModelProviderFactory },
        lifecycleOwner = { activity },
        instagramNavigator = instagramNavigator,
        twitterNavigator = twitterNavigator,
        facebookNavigator = facebookNavigator,
        phoneNavigator = phoneNavigator,
        webNavigator = webNavigator,
        menuBackNavigator = menuBackNavigator,
    )

}