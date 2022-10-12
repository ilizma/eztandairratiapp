package com.ilizma.view.adapter

import androidx.recyclerview.widget.ListAdapter
import com.ilizma.view.adapter.util.ItemDiffUtil
import com.ilizma.view.viewholder.ViewHolder

abstract class Adapter<T>(
    diffUtil: ItemDiffUtil<T>,
) : ListAdapter<T, ViewHolder<T>>(
    diffUtil,
)