package com.ilizma.domain.usecase.base

import io.reactivex.Flowable

interface FlowableUseCase<Type, in Params> {

    operator fun invoke(params: Params): Flowable<Type>

}