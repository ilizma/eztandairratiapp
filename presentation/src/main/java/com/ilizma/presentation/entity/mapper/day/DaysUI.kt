package com.ilizma.presentation.entity.mapper.day

import android.os.Parcelable
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RecyclerViewObject
import kotlinx.android.parcel.Parcelize

@Parcelize
class DaysUI(
    val day: String
) : RecyclerViewObject, Parcelable {

    override var itemViewType = RecyclerViewObject.ItemViewType.ITEM

}