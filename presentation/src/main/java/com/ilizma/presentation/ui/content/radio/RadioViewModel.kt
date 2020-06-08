package com.ilizma.presentation.ui.content.radio

import android.content.res.Resources
import androidx.lifecycle.LiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.presentation.media.MusicServiceConnection
import com.ilizma.presentation.ui.base.BaseViewModel
import dagger.Lazy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RadioViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : BaseViewModel() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    @Inject
    override lateinit var chuckerCollector: Lazy<ChuckerCollector>

    val ldRadioLoading: LiveData<Boolean> = musicServiceConnection.ldLoading

    val ldNetworkError: LiveData<Boolean> = musicServiceConnection.ldNetworkFailure

    val ldIsPlaying: LiveData<Boolean> = musicServiceConnection.ldIsPlaying

    fun start() {
        if (musicServiceConnection.ldIsConnected.value == true) {
            Completable.create { emitter ->
                try {
                    musicServiceConnection.transportControls.play()
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.tryOnError(e)
                }
            }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { radioLoading(true) }
                .subscribe({}, { throwable ->
                    handleFailure(throwable) { start() }
                })
                .addTo(compositeDisposable)
        }
    }

    fun stop() {
        if (musicServiceConnection.ldIsConnected.value == true) {
            Completable.create { emitter ->
                try {
                    musicServiceConnection.transportControls.stop()
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.tryOnError(e)
                }
            }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { radioLoading(true) }
                .doAfterTerminate { radioLoading(false) }
                .subscribe({
                    musicServiceConnection.ldIsPlaying.postValue(false)
                }, { throwable ->
                    handleFailure(throwable) { stop() }
                })
                .addTo(compositeDisposable)
        }
    }

    fun radioLoading(loading: Boolean) {
        musicServiceConnection.ldLoading.postValue(loading)
    }

    fun resetFailure() {
        musicServiceConnection.ldNetworkFailure.postValue(false)
    }

}