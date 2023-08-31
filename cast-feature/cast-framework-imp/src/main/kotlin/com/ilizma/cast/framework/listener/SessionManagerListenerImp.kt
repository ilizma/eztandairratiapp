package com.ilizma.cast.framework.listener

import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener

class SessionManagerListenerImp : SessionManagerListener<CastSession> {

    private lateinit var started: () -> Unit
    private lateinit var resumed: () -> Unit
    private lateinit var ended: () -> Unit

    fun init(
        started: () -> Unit,
        resumed: () -> Unit,
        ended: () -> Unit,
    ) {
        this.started = started
        this.resumed = resumed
        this.ended = ended
    }

    override fun onSessionStarting(session: CastSession) {
        //started()
    }

    override fun onSessionStarted(session: CastSession, sessionId: String) {
        started()
    }

    override fun onSessionStartFailed(session: CastSession, error: Int) {
        //ended()
    }

    override fun onSessionResuming(session: CastSession, sessionId: String) {
        //started()
    }

    override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
        resumed()
    }

    override fun onSessionResumeFailed(session: CastSession, error: Int) {
        ended()
    }

    override fun onSessionEnding(session: CastSession) {
        //ended()
    }

    override fun onSessionEnded(session: CastSession, error: Int) {
        ended()
    }

    override fun onSessionSuspended(session: CastSession, error: Int) {
        ended()
    }

}