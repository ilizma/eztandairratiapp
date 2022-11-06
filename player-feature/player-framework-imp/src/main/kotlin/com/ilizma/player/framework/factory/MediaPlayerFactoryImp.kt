package com.ilizma.player.framework.factory

import android.media.MediaPlayer

class MediaPlayerFactoryImp : MediaPlayerFactory<MediaPlayer> {

    override fun create(
    ): MediaPlayer = MediaPlayer()
}