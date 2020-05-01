package com.ilizma.domain.usecase.base

import io.reactivex.Observable

interface ObservableUseCase<Type, in Params> {

    operator fun invoke(params: Params): Observable<Type>

}