package com.ilizma.presentation.ui.content.schedule

import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.day.DaysUI
import com.ilizma.presentation.extensions.setOnReactiveClickListener
import com.ilizma.presentation.ui.customview.recycler.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_day.view.*
import javax.inject.Inject

class ScheduleAdapter @Inject constructor(
    onItemClick: (Pair<Int, String>) -> Unit
) : BaseAdapter<DaysUI>() {

    override var itemLayout: Int = R.layout.item_day
    override var itemShimmerLayout: Int = R.layout.item_day_shimmer
    override var itemEmptyLayout: Int = R.layout.item_empty

    override var onBindItem: (View, DaysUI) -> Unit = { itemView, daysUI ->
        with(itemView) {
            dayTxv.text = daysUI.indexedDayPair.second
            dayTxv.setOnReactiveClickListener {
                onItemClick(daysUI.indexedDayPair)
            }
        }
    }

}