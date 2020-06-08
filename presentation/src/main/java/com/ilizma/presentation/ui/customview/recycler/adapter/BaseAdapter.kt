package com.ilizma.presentation.ui.customview.recycler.adapter

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.inflate
import com.ilizma.presentation.ui.customview.recycler.layoutmanager.CustomLinearLayoutManager
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.NoActionObject
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RecyclerViewObject
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RecyclerViewObject.ItemViewType.*
import com.ilizma.presentation.ui.customview.recycler.recyclerobjects.RetryObject
import com.ilizma.presentation.ui.customview.recycler.viewholder.ErrorViewHolder
import com.ilizma.presentation.ui.customview.recycler.viewholder.EztandaViewHolder
import com.ilizma.presentation.ui.customview.recycler.viewholder.ItemViewHolder
import com.ilizma.presentation.ui.customview.recycler.viewholder.NoActionViewHolder

abstract class BaseAdapter<T : RecyclerViewObject> : RecyclerView.Adapter<EztandaViewHolder<T>>() {

    protected abstract var onBindItem: (View, T) -> Unit
    protected abstract var itemLayout: Int

    protected open var itemFullErrorLayout = R.layout.item_error
    protected open var itemEmptyLayout = R.layout.item_empty
    protected open var itemShimmerLayout = R.layout.item_schedule_detail_shimmer

    private var list = mutableListOf<RecyclerViewObject>()

    private var recyclerView: RecyclerView? = null
    protected var resources: Resources? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        resources = recyclerView.resources
        this.recyclerView = recyclerView
    }

    private fun clear() {
        recyclerView?.post {
            val size = list.size
            list.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    fun addItemsToList(itemList: List<RecyclerViewObject>) {
        if (itemList.isEmpty()) {
            if (list.any { it.itemViewType == EMPTY }) return
            checkScroll(false)
            addItem(EMPTY)
        } else {
            if (itemList == list) return
            checkScroll(true)
            addItem(ITEM, itemList)
        }
    }

    fun addLoadingPlaceholder(size: Int) {
        if (list.any { it.itemViewType == SHIMMER }) return

        checkScroll(true)
        val mutableList = mutableListOf<RecyclerViewObject>()
        for (i in 0 until size) {
            mutableList.add(NoActionObject().apply { itemViewType = SHIMMER })
        }
        addItem(SHIMMER, mutableList)
    }

    fun addError(onRetryClick: () -> Unit) {
        checkScroll(true)
        val onRetry = {
            clear()
            onRetryClick()
        }

        val viewType = ERROR
        val retryObject = RetryObject(onRetry, viewType)
        addItem(viewType, listOf(retryObject))
    }

    private fun checkScroll(canScroll: Boolean) {
        val layoutManager = recyclerView?.layoutManager
        if (layoutManager is CustomLinearLayoutManager) {
            layoutManager.canScrollVertically = canScroll
        }
    }

    @Synchronized
    @Suppress("UNCHECKED_CAST")
    private fun addItem(
        type: RecyclerViewObject.ItemViewType,
        newList: List<RecyclerViewObject>? = null
    ) {
        recyclerView?.post {
            val size = list.size
            list.clear()
            notifyItemRangeRemoved(0, size)

            when (type) {
                EMPTY -> {
                    val noActionObject = NoActionObject().apply { itemViewType = type }
                    list.add(noActionObject as T)
                    notifyItemInserted(0)
                }
                ERROR -> {
                    newList?.let {
                        list.addAll(it)
                        notifyItemInserted(0)
                    }
                }
                ITEM, SHIMMER -> {
                    newList?.let {
                        list.addAll(it)
                        notifyItemRangeInserted(0, it.size)
                    }
                }
            }
        }
    }

    protected fun getItemsList(): List<RecyclerViewObject> = list.filter { it.itemViewType == ITEM }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EztandaViewHolder<T> =
        when (viewType) {
            ERROR.ordinal -> ErrorViewHolder(parent.inflate(itemFullErrorLayout))
            ITEM.ordinal -> ItemViewHolder(parent.inflate(itemLayout), onBindItem)
            EMPTY.ordinal -> NoActionViewHolder(parent.inflate(itemEmptyLayout))
            SHIMMER.ordinal -> NoActionViewHolder(
                wrapWithShimmer(parent.inflate(itemShimmerLayout))
            )
            else -> ErrorViewHolder(parent.inflate(itemFullErrorLayout))
        }

    private fun wrapWithShimmer(itemView: View): View {
        // Wrap the view to inflate in a ShimmerFrameLayout for been able to show Shimmer effect
        val shimmerFrameLayout = ShimmerFrameLayout(itemView.context).apply {
            val layoutManager = recyclerView?.layoutManager
            val width = if (layoutManager is LinearLayoutManager) {
                if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                    FrameLayout.LayoutParams.WRAP_CONTENT
                } else {
                    FrameLayout.LayoutParams.MATCH_PARENT
                }
            } else {
                FrameLayout.LayoutParams.MATCH_PARENT
            }

            id = R.id.parentContainer
            layoutParams = FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT)
            addView(itemView)
        }

        shimmerFrameLayout.startShimmer()
        return shimmerFrameLayout
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: EztandaViewHolder<T>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int) = list[position].itemViewType.ordinal

}