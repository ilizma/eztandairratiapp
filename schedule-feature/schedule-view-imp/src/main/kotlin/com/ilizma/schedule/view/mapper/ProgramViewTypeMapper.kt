package com.ilizma.schedule.view.mapper

import com.ilizma.schedule.view.model.ProgramViewType
import com.ilizma.schedule.view.model.ProgramViewType.TYPE_ITEM
import com.ilizma.schedule.view.model.ProgramViewType.TYPE_LOADING

class ProgramViewTypeMapper {

    fun from(
        viewType: Int,
    ): ProgramViewType = when (viewType) {
        TYPE_ITEM.ordinal -> TYPE_ITEM
        TYPE_LOADING.ordinal -> TYPE_LOADING
        else -> throw IllegalArgumentException("Invalid view type: $viewType")
    }

}