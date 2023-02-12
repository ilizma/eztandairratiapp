package com.ilizma.schedule.flow.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Day(
    val id: Int,
    val name: String,
) : Parcelable
