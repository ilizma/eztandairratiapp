package com.ilizma.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T>(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: T)

    abstract fun unBind()

}