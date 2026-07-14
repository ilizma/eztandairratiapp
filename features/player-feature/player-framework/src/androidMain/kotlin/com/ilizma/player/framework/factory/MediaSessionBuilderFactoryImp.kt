package com.ilizma.player.framework.factory

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession

class MediaSessionBuilderFactoryImp(
    private val context: Context,
) : MediaSessionBuilderFactory<MediaSession.Builder, ExoPlayer> {

    override fun create(
        player: ExoPlayer,
    ): MediaSession.Builder = MediaSession.Builder(context, player)
}