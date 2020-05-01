package com.ilizma.eztandairratiapp.di.module.presentation

import com.ilizma.presentation.ui.content.gallery.GalleryFragment
import com.ilizma.presentation.ui.content.home.HomeFragment
import com.ilizma.presentation.ui.content.slideshow.SlideshowFragment
import com.ilizma.eztandairratiapp.di.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun home(): HomeFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun gallery(): GalleryFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun slideshow(): SlideshowFragment

}
