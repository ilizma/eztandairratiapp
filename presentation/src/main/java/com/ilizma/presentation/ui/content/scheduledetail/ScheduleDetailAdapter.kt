package com.ilizma.presentation.ui.content.scheduledetail

import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.schedule.ScheduleUI
import com.ilizma.presentation.extensions.gone
import com.ilizma.presentation.extensions.visible
import com.ilizma.presentation.ui.customview.recycler.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_programme.view.*
import javax.inject.Inject

class ScheduleDetailAdapter @Inject constructor() : BaseAdapter<ScheduleUI>() {

    override var itemLayout: Int = R.layout.item_programme
    override var itemShimmerLayout: Int = R.layout.item_programme_shimmer
    override var itemEmptyLayout: Int = R.layout.item_empty

    override var onBindItem: (View, ScheduleUI) -> Unit = { itemView, scheduleUI ->
        with(itemView) {
            programmeHourTxv.text = resources.getString(R.string.hour, scheduleUI.hour)
            programmeNameTxv.text = scheduleUI.name
            when (scheduleUI.repeated.toInt()) {
                0 -> programmeRepeatedImv.gone()
                1 -> programmeRepeatedImv.visible()
            }
        }
    }

}