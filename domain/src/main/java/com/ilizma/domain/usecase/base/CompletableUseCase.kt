package com.ilizma.domain.usecase.base

import io.reactivex.rxjava3.core.Completable

interface CompletableUseCase<in Params> {

    operator fun invoke(params: Params): Completable

}