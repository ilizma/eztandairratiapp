package com.ilizma.review.di

import com.ilizma.review.framework.di.playReviewFrameworkModule
import org.koin.core.module.Module

val reviewModules: List<Module> = listOf(
    playReviewFrameworkModule,
)