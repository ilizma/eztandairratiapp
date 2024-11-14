package com.ilizma.view.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
expect fun <T> Flow<T>.collectAsStateMultiplatform(
    initialValue: T,
    context: CoroutineContext = EmptyCoroutineContext,
): State<T>