package com.ilizma.schedule.flow.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Day(
    val id: Int,
    val name: String,
) : Parcelable
