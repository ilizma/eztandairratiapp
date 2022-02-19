package com.ilizma.schedule.presentation.viewmodel.di

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.ilizma.di.viewmodel.ViewModelKey
import com.ilizma.presentation.SingleLiveEvent
import com.ilizma.schedule.di.R
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.presentation.mapper.ProgramListMapper
import com.ilizma.schedule.presentation.mapper.ProgramMapper
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailViewModelImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(ScheduleDetailViewModel::class)
    fun provideScheduleDetailViewModel(
        dayNameUseCase: DayNameUseCase,
        scheduleUseCase: ScheduleUseCase,
        resources: Resources,
    ): ScheduleDetailViewModel = ScheduleDetailViewModelImp(
        dayNameUseCase = dayNameUseCase,
        scheduleUseCase = scheduleUseCase,
        mapper = ProgramListMapper(ProgramMapper()),
        backgroundScheduler = Schedulers.io(),
        compositeDisposable = CompositeDisposable(),
        unknownErrorMessage = resources.getString(R.string.unknown_error),
        _dayName = MutableLiveData(),
        _schedule = MutableLiveData(),
        _error = MutableLiveData(),
        _navigationAction = SingleLiveEvent(),
    )

}