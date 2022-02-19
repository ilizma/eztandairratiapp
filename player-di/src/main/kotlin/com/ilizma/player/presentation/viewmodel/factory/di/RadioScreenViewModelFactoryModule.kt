package com.ilizma.player.presentation.viewmodel.factory.di

import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelAssistedFactory
import com.ilizma.player.presentation.viewmodel.factory.RadioScreenViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object RadioScreenViewModelFactoryModule {

    /* @Provides
     @IntoMap
     @ViewModelKey(RadioScreenViewModel::class)
     fun provideRadioScreenViewModel(
         stateUseCase: PlayerStateUseCase,
         playUseCase: PlayerPlayUseCase,
         stopUseCase: PlayerStopUseCase,
     ): RadioScreenViewModel = RadioScreenViewModelImp(
         stateUseCase = stateUseCase,
         playUseCase = playUseCase,
         stopUseCase = stopUseCase,
         mapper = PlayerStateMapper(),
         backgroundScheduler = Schedulers.io(),
         compositeDisposable = CompositeDisposable(),
         _playerState = MutableLiveData(),
         _navigationAction = SingleLiveEvent(),
     )*/

    @Provides
    fun provideRadioScreenViewModelFactory(
        radioScreenViewModelAssistedFactory: RadioScreenViewModelAssistedFactory,
    ): ViewModelProvider.Factory = RadioScreenViewModelFactory(
        radioScreenViewModelAssistedFactory,
    )

}