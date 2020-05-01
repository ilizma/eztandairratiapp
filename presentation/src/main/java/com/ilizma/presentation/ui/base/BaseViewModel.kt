package com.ilizma.presentation.ui.base

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.domain.entity.base.Failure
import com.ilizma.presentation.BuildConfig
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.getFailureMessage
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resources: Lazy<Resources>

    @Inject
    lateinit var chuckerCollector: Lazy<ChuckerCollector>

    private var _ldFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure> = _ldFailure
    private var _ldLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean> = _ldLoading

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun loading(visible: Boolean) {
        _ldLoading.value = visible
    }

    protected fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = getFailure(throwable, retryAction)
        chuckerCollector.get().onError(failure.getFailureMessage(), failure)
        _ldFailure.value = failure
    }

    protected fun getFailure(throwable: Throwable, retryAction: () -> Unit): Failure {
        val failure = throwable as? Failure ?: Failure.Error(
            if (throwable.message?.isNotEmpty() == true) {
                @Suppress("ConstantConditionIf")
                if (BuildConfig.BUILD_TYPE == "release") {
                    resources.get().getString(R.string.unknown_error)
                } else {
                    throwable.message ?: resources.get().getString(R.string.unknown_error)
                }
            } else {
                resources.get().getString(R.string.unknown_error)
            }
        )

        failure.retryAction = retryAction
        return failure
    }

}