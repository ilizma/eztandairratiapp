package com.ilizma.player.presentation.viewmodel.factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.presentation.SingleLiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RadioScreenViewModelFactory(
    private val radioScreenViewModelAssistedFactory: RadioScreenViewModelAssistedFactory,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = radioScreenViewModelAssistedFactory.create(
        mapper = PlayerStateMapper(),
        backgroundScheduler = Schedulers.io(),
        compositeDisposable = CompositeDisposable(),
        _playerState = MutableLiveData(),
        _navigationAction = SingleLiveEvent(),
    ) as T

}