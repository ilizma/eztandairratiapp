package com.ilizma.player.framework.factory

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer

class PlayerFactoryImp(
    private val context: Context,
) : PlayerFactory<ExoPlayer> {

    override fun create(
    ): ExoPlayer = ExoPlayer.Builder(context).build()
}