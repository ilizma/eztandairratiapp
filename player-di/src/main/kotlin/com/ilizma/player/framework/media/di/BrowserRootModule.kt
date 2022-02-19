package com.ilizma.player.framework.media.di

import android.content.res.Resources
import androidx.media.MediaBrowserServiceCompat.BrowserRoot
import com.ilizma.player.di.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object BrowserRootModule {

    @Provides
    fun provideBrowserRoot(
        resources: Resources,
    ): BrowserRoot = BrowserRoot(resources.getString(R.string.app_name), null)
}