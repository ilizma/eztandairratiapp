package com.ilizma.domain.repository

import io.reactivex.Completable

interface LoginRepository {

    fun login(): Completable

    fun nuke(): Completable

}