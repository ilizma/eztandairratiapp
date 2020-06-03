package com.ilizma.presentation.entity.mapper.schedule

import android.os.Parcelable
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RecyclerViewObject
import kotlinx.android.parcel.Parcelize

@Parcelize
class ScheduleUI(
    val hour: String,
    val day: String,
    val name: String,
    val repeated: String
) : RecyclerViewObject, Parcelable {

    override var itemViewType = RecyclerViewObject.ItemViewType.ITEM

}