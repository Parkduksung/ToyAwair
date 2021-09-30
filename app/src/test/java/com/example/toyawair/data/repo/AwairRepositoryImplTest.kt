package com.example.toyawair.data.repo

import base.BaseTest
import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.data.source.remote.AwairRemoteDataSource
import com.example.toyawair.data.source.remote.AwairRemoteDataSourceImpl
import com.example.toyawair.data.source.remote.AwairRemoteDataSourceImplTest
import com.example.toyawair.utils.Result
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class AwairRepositoryImplTest : BaseTest() {


    @Mock
    lateinit var awairRemoteDataSource: AwairRemoteDataSource

    private lateinit var awairRepositoryImpl: AwairRepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        awairRemoteDataSource = Mockito.mock(AwairRemoteDataSourceImpl::class.java)
        awairRepositoryImpl = AwairRepositoryImpl(awairRemoteDataSource)
    }

    @Test
    fun checkGetEventsSuccessTest() = runBlocking {

        val successResult = Result.Success(AwairRemoteDataSourceImplTest.mockAwairResponse)

        Mockito.`when`(awairRemoteDataSource.getEvent("")).thenReturn(successResult)

        MatcherAssert.assertThat(
            "올바른 AwairResponse 값이 나오므로 성공.",
            (awairRepositoryImpl.getEvent("") as Result.Success<AwairResponse>).data,
            Matchers.`is`(successResult.data)
        )

    }

    @Test
    fun checkGetEventsFailTest() = runBlocking {

        val failResult = Result.Error(Exception("에러가 발생하였습니다."))

        Mockito.`when`(awairRemoteDataSource.getEvent("")).then { failResult }

        MatcherAssert.assertThat(
            "Exception 발생했으므로 실패.",
            (awairRepositoryImpl.getEvent("") as Result.Error).exception.message,
            Matchers.`is`(failResult.exception.message)
        )

    }


}