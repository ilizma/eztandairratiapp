package com.ilizma.player.framework.media.di

import androidx.media.MediaBrowserServiceCompat.BrowserRoot
import com.ilizma.player.di.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object BrowserRootModule {

    @Provides
    fun provideBrowserRoot(
    ): BrowserRoot = BrowserRoot(getString(R.string.app_name), null)
}