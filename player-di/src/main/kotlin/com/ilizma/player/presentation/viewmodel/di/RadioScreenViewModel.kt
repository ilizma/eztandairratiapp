package com.ilizma.player.presentation.viewmodel.di

import androidx.lifecycle.MutableLiveData
import com.ilizma.di.viewmodel.ViewModelKey
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModelImp
import com.ilizma.presentation.SingleLiveEvent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RadioScreenViewModel::class)
    fun radio(
        stateUseCase: PlayerStateUseCase,
    ): RadioScreenViewModel = RadioScreenViewModelImp(
        useCase = stateUseCase,
        mapper = PlayerStateMapper(),
        backgroundScheduler = Schedulers.io(),
        compositeDisposable = CompositeDisposable(),
        _playerState = MutableLiveData(),
        _navigationAction = SingleLiveEvent(),
    )

}