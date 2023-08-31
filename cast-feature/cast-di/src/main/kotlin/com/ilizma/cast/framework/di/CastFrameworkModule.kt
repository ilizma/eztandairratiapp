package com.ilizma.cast.framework.di

import android.app.Activity
import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.CastFrameworkImp
import com.ilizma.cast.framework.listener.CastStateListenerImp
import com.ilizma.cast.framework.listener.SessionManagerListenerImp
import com.ilizma.cast.framework.model.CastState.DISCONNECTED
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.resources.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.reactivex.rxjava3.subjects.BehaviorSubject

@Module
@InstallIn(ActivityComponent::class)
object CastFrameworkModule {

    @Provides
    fun provideCastFramework(
        activity: Activity,
        playerFramework: PlayerFramework,
    ): CastFramework = CastFrameworkImp(
        activity = activity,
        castStateSubject = BehaviorSubject.createDefault(DISCONNECTED),
        castStateListener = CastStateListenerImp(),
        sessionManagerListener = SessionManagerListenerImp(),
        title = activity.getString(R.string.radio_name),
        subtitle = activity.getString(R.string.free_radio),
        image = "https://www.eztanda.com/images/eztanda_logo.png",
        playerFramework = playerFramework,
    )

}