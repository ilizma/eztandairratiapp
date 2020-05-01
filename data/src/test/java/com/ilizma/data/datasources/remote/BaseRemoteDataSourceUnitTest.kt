package com.ilizma.data.datasources.remote

import com.ilizma.data.entity.base.ResponseObjectImpl
import com.ilizma.data.extensions.*
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseRemoteDataSourceUnitTest {

    private var baseRemoteDataSource = BaseRemoteDataSource()

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

    @Test
    fun `modifySingleList should return value`() {
        val resultSingle = getSingleListResultSuccess(listOf(ResponseObjectImpl()))

        val testObserver = baseRemoteDataSource.modifySingleList(resultSingle).test()

        testObserver.assertGeneralsSuccess()
    }

    @Test
    fun `modifySingleList should return Failure`() {
        val resultSingle = getSingleListResultError<List<ResponseObjectImpl>>()

        val testObserver = baseRemoteDataSource.modifySingleList(resultSingle).test()

        testObserver.assertGeneralsError()
    }

    @Test
    fun `modifySingleSuccess should return value`() {
        val resultSingle = getSingleResultSuccess(createResponseBody())

        val testObserver = baseRemoteDataSource.modifySingleSuccess(resultSingle).test()

        testObserver.assertGeneralsSuccess()
    }

    @Test
    fun `modifySingleSuccess should return Failure`() {
        val resultSingle = getSingleResultError<ResponseBody>()

        val testObserver = baseRemoteDataSource.modifySingleSuccess(resultSingle).test()

        testObserver.assertGeneralsError()
    }

}