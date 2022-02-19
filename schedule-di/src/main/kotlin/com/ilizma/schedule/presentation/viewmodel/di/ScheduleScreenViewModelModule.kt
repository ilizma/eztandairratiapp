package com.ilizma.schedule.presentation.viewmodel.di

import androidx.lifecycle.MutableLiveData
import com.ilizma.di.viewmodel.ViewModelKey
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.domain.usecase.DaysUseCase
import com.ilizma.schedule.presentation.mapper.DayListMapper
import com.ilizma.schedule.presentation.mapper.DayMapper
import com.ilizma.schedule.presentation.mapper.DaysMapper
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModelImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
object ScheduleScreenViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(ScheduleScreenViewModel::class)
    fun provideScheduleScreenViewModel(
        useCase: DaysUseCase,
    ): ScheduleScreenViewModel = ScheduleScreenViewModelImp(
        useCase = useCase,
        mapper = DaysMapper(DayListMapper(DayMapper())),
        _days = MutableLiveData(),
        _navigationAction = SingleLiveEvent(),
    )

}