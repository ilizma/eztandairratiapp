package com.ilizma.presentation.ui.content.scheduledetail

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.domain.usecase.schedule.GetScheduleFromLocalParams
import com.ilizma.domain.usecase.schedule.GetScheduleFromLocalUseCase
import com.ilizma.presentation.entity.mapper.schedule.ScheduleMapperUI
import com.ilizma.presentation.entity.mapper.schedule.ScheduleUI
import com.ilizma.presentation.ui.base.BaseViewModel
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class ScheduleDetailViewModel @Inject constructor(
    private val getScheduleFromLocalUseCase: GetScheduleFromLocalUseCase
) : BaseViewModel() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    @Inject
    override lateinit var chuckerCollector: Lazy<ChuckerCollector>

    private val _ldScheduleUIList = MutableLiveData<List<ScheduleUI>>()
    val ldScheduleUIList: LiveData<List<ScheduleUI>> = _ldScheduleUIList

    fun getSchedule(day: Int) {
        getScheduleFromLocalUseCase(GetScheduleFromLocalParams(day))
            .map { scheduleList ->
                scheduleList.map { ScheduleMapperUI().mapToUI(it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading(true) }
            .doAfterTerminate { loading(false) }
            .subscribe({ scheduleUIList ->
                _ldScheduleUIList.postValue(scheduleUIList)
            }, { throwable ->
                handleFailure(throwable) { getSchedule(day) }
            })
            .addTo(compositeDisposable)

    }

}