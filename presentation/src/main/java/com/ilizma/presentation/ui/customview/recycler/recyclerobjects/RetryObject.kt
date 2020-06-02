package com.ilizma.presentation.ui.customview.recycler.recyclerobjects

class RetryObject(
    val onRetryClick: () -> Unit,
    viewType: RecyclerViewObject.ItemViewType
) : RecyclerViewObject {

    override var itemViewType = viewType

}