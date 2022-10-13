package com.ilizma.review.framework.di

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory
import com.ilizma.review.framework.PlayReviewFramework
import com.ilizma.review.framework.PlayReviewFrameworkImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object PlayReviewFrameworkModule {

    @Provides
    fun providePlayReviewFramework(
        activity: Activity,
        @ApplicationContext context: Context,
    ): PlayReviewFramework = PlayReviewFrameworkImp(
        activity = activity,
        manager = ReviewManagerFactory.create(context),
    )

}