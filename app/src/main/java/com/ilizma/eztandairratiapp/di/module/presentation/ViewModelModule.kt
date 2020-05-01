package com.ilizma.eztandairratiapp.di.module.presentation

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.ui.base.BaseViewModel
import com.ilizma.presentation.ui.content.gallery.GalleryViewModel
import com.ilizma.presentation.ui.content.home.HomeViewModel
import com.ilizma.presentation.ui.content.slideshow.SlideshowViewModel
import com.ilizma.eztandairratiapp.di.viewmodel.ViewModelFactory
import com.ilizma.eztandairratiapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun home(vm: HomeViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun gallery(vm: GalleryViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SlideshowViewModel::class)
    abstract fun slideshow(vm: SlideshowViewModel): BaseViewModel

}
