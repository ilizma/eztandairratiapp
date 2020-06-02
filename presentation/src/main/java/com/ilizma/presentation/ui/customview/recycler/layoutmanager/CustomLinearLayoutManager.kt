package com.ilizma.presentation.ui.customview.recycler.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLinearLayoutManager(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL
) : LinearLayoutManager(context, orientation, false) {

    var canScrollVertically = true

    override fun canScrollVertically(): Boolean = canScrollVertically

}