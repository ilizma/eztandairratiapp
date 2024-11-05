package com.ilizma.review.framework.di

import com.google.android.play.core.review.ReviewManagerFactory
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.review.framework.PlayReviewFramework
import com.ilizma.review.framework.PlayReviewFrameworkImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val playReviewFrameworkModule: Module = module {

    scope<MainActivity> {
        scoped<PlayReviewFramework> {
            PlayReviewFrameworkImp(
                activity = get(),
                manager = { ReviewManagerFactory.create(androidContext()) },
            )
        }
    }

}