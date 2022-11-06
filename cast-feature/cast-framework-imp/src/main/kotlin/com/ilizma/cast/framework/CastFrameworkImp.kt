package com.ilizma.cast.framework

import android.app.Activity
import android.net.Uri
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
import com.ilizma.cast.framework.imp.BuildConfig
import com.ilizma.cast.framework.listener.CastStateListenerImp
import com.ilizma.cast.framework.listener.SessionManagerListenerImp
import com.ilizma.cast.framework.model.CastState
import com.ilizma.player.framework.PlayerFramework
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject

class CastFrameworkImp(
    private val activity: Activity,
    private val castStateSubject: Subject<CastState>,
    private val castStateListener: CastStateListenerImp,
    private val sessionManagerListener: SessionManagerListenerImp,
    private val title: String,
    private val subtitle: String,
    private val image: String,
    private val playerFramework: PlayerFramework,
) : CastFramework {

    private var castContext: CastContext? = null
    private var sessionManager: SessionManager? = null

    override fun init() {
        castContext = CastContext.getSharedInstance()
        sessionManager = castContext?.sessionManager
    }

    override val castState: Observable<CastState> = castStateSubject

    override fun <T> setUpMediaRouteButton(
        menu: T,
        menuResourceId: Int,
    ) {
        CastButtonFactory.setUpMediaRouteButton(activity, menu as Menu, menuResourceId)

        (menu as Menu).findItem(menuResourceId)
            .let { it.actionView as MediaRouteButton }
            .let { castStateListener.init { castStateSubject.onNext(it) } }
        sessionManagerListener.init(
            started = { play() },
            resumed = { /*resume()*/ },
            ended = { /*stop()*/ })
    }

    override fun onResume() {
        castContext?.addCastStateListener(castStateListener)
        sessionManager?.addSessionManagerListener(sessionManagerListener, CastSession::class.java)
    }

    override fun onDestroy() {
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
        Uri.parse(image)
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