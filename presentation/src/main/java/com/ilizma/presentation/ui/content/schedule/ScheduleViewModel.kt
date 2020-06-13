package com.ilizma.presentation.ui.content.schedule

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.domain.entity.base.Failure
import com.ilizma.domain.usecase.schedule.GetScheduleUseCase
import com.ilizma.domain.usecase.schedule.IsScheduleFromLocalUseCase
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.day.DaysMapperUI
import com.ilizma.presentation.entity.mapper.day.DaysUI
import com.ilizma.presentation.ui.base.BaseViewModel
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
    private val isScheduleFromLocalUseCase: IsScheduleFromLocalUseCase
) : BaseViewModel() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    @Inject
    override lateinit var chuckerCollector: Lazy<ChuckerCollector>

    private val _ldDaysUIList = MutableLiveData<List<DaysUI>>()
    val ldDaysUIList: LiveData<List<DaysUI>> = _ldDaysUIList

    init {
        getSchedule()
    }

    private fun getSchedule() {
        getScheduleUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading(true) }
            .subscribe({
                loading(false)
                returnDaysUIList()
            }, { throwable ->
                if (throwable is Failure.NoInternet) {
                    getLocalSchedule()
                } else {
                    handleFailure(throwable) { getSchedule() }
                }
            })
            .addTo(compositeDisposable)

    }

    private fun getLocalSchedule() {
        isScheduleFromLocalUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { loading(false) }
            .subscribe({
                returnDaysUIList()
            }, { throwable ->
                handleFailure(
                    if (throwable is Failure.NotInDatabase) {
                        Failure.NoInternet(resources.get().getString(R.string.no_internet))
                    } else {
                        throwable
                    }
                ) { getLocalSchedule() }
            })
            .addTo(compositeDisposable)

    }

    private fun returnDaysUIList() {
        _ldDaysUIList.value =
            resources.get().getStringArray(R.array.days_array).mapIndexed { index, day ->
                DaysMapperUI().mapToUI(Pair(index, day))
            }
    }

}