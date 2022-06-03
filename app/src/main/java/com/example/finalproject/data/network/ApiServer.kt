package com.example.finalproject.data.network

import com.example.finalproject.data.response.DzikirPagiResponse
import com.example.finalproject.data.response.DzikirPetangResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiServer {

    @GET("dzikir-pagi")
    fun getDzikirPagi(): Flowable<List<DzikirPagiResponse>>

    @GET("dzikir-petang")
    fun getDzikirPetang(): Flowable<List<DzikirPetangResponse>>

}