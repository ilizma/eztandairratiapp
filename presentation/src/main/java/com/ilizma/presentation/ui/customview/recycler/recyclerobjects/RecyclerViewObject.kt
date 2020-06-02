package com.ilizma.presentation.ui.customview.recycler.recyclerobjects

interface RecyclerViewObject {

    var itemViewType: ItemViewType

    enum class ItemViewType {
        ERROR,
        ITEM,
        EMPTY,
        SHIMMER
    }

}