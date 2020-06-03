package com.ilizma.eztandairratiapp.di.module.presentation

import androidx.lifecycle.ViewModelProvider
import com.ilizma.eztandairratiapp.di.viewmodel.ViewModelFactory
import com.ilizma.eztandairratiapp.di.viewmodel.ViewModelKey
import com.ilizma.presentation.ui.base.BaseViewModel
import com.ilizma.presentation.ui.content.menu.MenuViewModel
import com.ilizma.presentation.ui.content.radio.RadioViewModel
import com.ilizma.presentation.ui.content.schedule.ScheduleViewModel
import com.ilizma.presentation.ui.content.scheduledetail.ScheduleDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RadioViewModel::class)
    abstract fun radio(vm: RadioViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun schedule(vm: ScheduleViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleDetailViewModel::class)
    abstract fun scheduleDetail(vm: ScheduleDetailViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun menu(vm: MenuViewModel): BaseViewModel

}
