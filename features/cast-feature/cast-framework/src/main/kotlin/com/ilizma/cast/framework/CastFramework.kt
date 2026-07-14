package com.ilizma.cast.framework

import android.content.Context
import android.view.Menu
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManager
import com.google.android.gms.common.images.WebImage
import com.ilizma.cast.framework.BuildConfig
import com.ilizma.cast.framework.listener.CastStateListener
import com.ilizma.cast.framework.listener.SessionManagerListener
import com.ilizma.player.framework.PlayerFramework
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.core.net.toUri
import com.ilizma.cast.framework.model.CastState
import kotlin.jvm.java

class CastFramework(
    private val context: Context,
    private val _castStateFlow: MutableStateFlow<CastState>,
    private val castStateListener: CastStateListener,
    private val sessionManagerListener: SessionManagerListener,
    private val title: String,
    private val subtitle: String,
    private val image: String,
    private val playerFramework: PlayerFramework,
) {

    private var castContext: CastContext? = null
    private var sessionManager: SessionManager? = null

    fun init() {
        castContext = CastContext.getSharedInstance()
        sessionManager = castContext?.sessionManager
    }

    val castState: Flow<CastState> = _castStateFlow

    fun <T> setUpMediaRouteButton(
        menu: T,
        menuResourceId: Int,
    ) {
        CastButtonFactory.setUpMediaRouteButton(context, menu as Menu, menuResourceId)

        (menu as Menu).findItem(menuResourceId)
            .let { it.actionView as MediaRouteButton }
            .let { castStateListener.init { _castStateFlow.value = it } }
        sessionManagerListener.init(
            started = { play() },
            resumed = { /*resume()*/ },
            ended = { /*stop()*/ })
    }

    fun onResume() {
        castContext?.addCastStateListener(castStateListener)
        sessionManager?.addSessionManagerListener(sessionManagerListener, CastSession::class.java)
    }

    fun onDestroy() {
        castContext?.removeCastStateListener(castStateListener)
        sessionManager?.removeSessionManagerListener(
            sessionManagerListener,
            CastSession::class.java
        )
    }

    private fun play() {
        val metadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MUSIC_TRACK)
        metadata.putString(MediaMetadata.KEY_TITLE, title)
        metadata.putString(MediaMetadata.KEY_SUBTITLE, subtitle)
        image.toUri()
            .let { WebImage(it) }
            .let { metadata.addImage(it) }

        MediaInfo.Builder(BuildConfig.AUDIO_URL)
            .setStreamType(MediaInfo.STREAM_TYPE_LIVE)
            .setContentType("audio/mpeg")
            .setMetadata(metadata)
            .build()
            .let {
                MediaLoadRequestData.Builder()
                    .setMediaInfo(it)
                    .build()
            }
            .let { sessionManager?.currentCastSession?.remoteMediaClient?.load(it) }
            .also { playerFramework.cancel() }
    }

}