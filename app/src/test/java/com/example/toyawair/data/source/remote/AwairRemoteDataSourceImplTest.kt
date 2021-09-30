package com.example.toyawair.data.source.remote

import base.BaseTest
import com.example.toyawair.api.AwairApi
import com.example.toyawair.api.response.AwairResponse
import com.example.toyawair.api.response.Event
import com.example.toyawair.utils.Result
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AwairRemoteDataSourceImplTest : BaseTest() {

    @Mock
    lateinit var awairApi: AwairApi

    private lateinit var awairRemoteDataSourceImpl: AwairRemoteDataSourceImpl

    @Before
    fun setUp() {
        super.setup()
        awairApi = Mockito.mock(awairApi::class.java)
        awairRemoteDataSourceImpl = AwairRemoteDataSourceImpl(awairApi)
    }

    @Test
    fun checkGetEventsSuccessTest() = runBlocking {

        initMockAwairApi()

        val successResult = Result.Success(mockAwairResponse)

        MatcherAssert.assertThat(
            "올바른 AwairResponse 값이 나오므로 성공.",
            (awairRemoteDataSourceImpl.getEvent("") as Result.Success<AwairResponse>).data,
            Matchers.`is`(successResult.data)
        )

    }

    @Test
    fun checkGetEventsFailTest() = runBlocking {

        val failResult = Result.Error(Exception("에러가 발생하였습니다."))

        Mockito.`when`(awairApi.getEvents("")).then { failResult }

        MatcherAssert.assertThat(
            "Exception 발생했으므로 실패.",
            (awairRemoteDataSourceImpl.getEvent("") as Result.Error).exception.message,
            Matchers.`is`(failResult.exception.message)
        )

    }


    private fun initMockAwairApi() {
        Mockito.`when`(awairApi.getEvents("")).thenReturn(
            object : Call<AwairResponse> {
                override fun clone(): Call<AwairResponse> {
                    TODO("Not yet implemented")
                }

                override fun execute(): Response<AwairResponse> {
                    return Response.success(mockAwairResponse)
                }

                override fun enqueue(callback: Callback<AwairResponse>) {
                    TODO("Not yet implemented")
                }

                override fun isExecuted(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun cancel() {
                    TODO("Not yet implemented")
                }

                override fun isCanceled(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }
            }
        )
    }


    companion object {
        val mockAwairResponse =
            AwairResponse(
                events = listOf(
                    Event(
                        end = "November 8, 2017 1:30 PM",
                        start = "November 8, 2017 12:30 PM",
                        title = "Nap Break"
                    ),
                    Event(
                        end = "November 3, 2017 10:00 PM",
                        start = "November 3, 2017 6:00 PM",
                        title = "Football Game"
                    ),
                    Event(
                        end = "November 6, 2017 10:00 PM",
                        start = "November 6, 2017 5:00 PM",
                        title = "Evening Cookout with Friends"
                    )
                ),
                next_page_token = "Q2dFMg"
            )
    }

}