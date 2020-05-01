package com.ilizma.domain.usecase.login
import com.ilizma.domain.repository.LoginRepository
import com.ilizma.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : CompletableUseCase<Unit> {

    override fun invoke(params: Unit): Completable =
        loginRepository.login()

}