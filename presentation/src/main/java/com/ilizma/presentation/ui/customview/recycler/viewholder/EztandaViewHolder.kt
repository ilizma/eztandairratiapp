package com.ilizma.presentation.ui.customview.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.safeLet
import com.ilizma.presentation.extensions.setOnReactiveClickListener
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RecyclerViewObject
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RetryObject

abstract class EztandaViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: RecyclerViewObject)

}

class NoActionViewHolder<in T>(val view: View) : EztandaViewHolder<T>(view) {

    override fun bind(item: RecyclerViewObject) {}

}

class ErrorViewHolder<in T>(val view: View) : EztandaViewHolder<T>(view) {

    override fun bind(item: RecyclerViewObject) {
        val retryButton = view.findViewById<View?>(R.id.retryBtn)
        val retryObject = item as? RetryObject
        safeLet(retryButton, retryObject) { button, retry ->
            button.setOnReactiveClickListener { retry.onRetryClick() }
        }
    }

}

class ItemViewHolder<T : RecyclerViewObject>(itemView: View, val onBindItem: (View, T) -> Unit) :
    EztandaViewHolder<T>(itemView) {

    @Suppress("UNCHECKED_CAST")
    override fun bind(item: RecyclerViewObject) {
        onBindItem(itemView, item as T)
    }

}