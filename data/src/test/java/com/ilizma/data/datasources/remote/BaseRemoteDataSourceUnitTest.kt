package com.ilizma.data.datasources.remote

import android.content.res.Resources
import com.ilizma.data.entity.base.ResponseObjectImpl
import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.data.extensions.getSingleResultError
import com.ilizma.data.extensions.getSingleResultSuccess
import com.squareup.moshi.Moshi
import dagger.Lazy
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class BaseRemoteDataSourceUnitTest {

    private var baseRemoteDataSource = object : BaseRemoteDataSource() {
        @Inject
        override lateinit var resources: Lazy<Resources>

        @Inject
        override lateinit var moshi: Lazy<Moshi>
    }

    @Test
    fun `modifySingle should return value`() {
        val resultSingle = getSingleResultSuccess(ResponseObjectImpl())

        val testObserver = baseRemoteDataSource.modifySingle(resultSingle).test()

        testObserver.assertGeneralsSuccess()
    }

    @Test
    fun `modifySingle should return Failure`() {
        val resultSingle = getSingleResultError<ResponseObjectImpl>()

        val testObserver = baseRemoteDataSource.modifySingle(resultSingle).test()

        testObserver.assertGeneralsError()
    }

}