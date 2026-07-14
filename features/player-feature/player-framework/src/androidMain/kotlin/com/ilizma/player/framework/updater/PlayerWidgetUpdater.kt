package com.ilizma.player.framework.updater

import android.content.Context
import com.ilizma.player.framework.model.PlayerState

interface PlayerWidgetUpdater {

    suspend fun updateMediaWidget(
        context: Context,
        playerState: PlayerState,
    )

}