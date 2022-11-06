package com.ilizma.cast.framework.di

import android.app.Activity
import androidx.fragment.app.Fragment
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
import dagger.hilt.android.components.FragmentComponent
import io.reactivex.rxjava3.subjects.BehaviorSubject

@Module
@InstallIn(FragmentComponent::class)
object CastFrameworkModule {

    @Provides
    fun provideCastFramework(
        activity: Activity,
        fragment: Fragment,
        playerFramework: PlayerFramework,
    ): CastFramework = CastFrameworkImp(
        activity = activity,
        castStateSubject = BehaviorSubject.createDefault(DISCONNECTED),
        castStateListener = CastStateListenerImp(),
        sessionManagerListener = SessionManagerListenerImp(),
        title = fragment.getString(R.string.radio_name),
        subtitle = fragment.getString(R.string.free_radio),
        image = "https://www.eztanda.com/images/eztanda_logo.png",
        playerFramework = playerFramework,
    )

}