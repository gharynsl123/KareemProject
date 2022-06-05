package com.example.finalproject.data.network.dzikir

import com.example.finalproject.data.response.DzikirPagiResponseItem
import com.example.finalproject.data.response.DzikirPetangResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface DzikirApiService {

    @GET("dzikir-pagi")
    fun getDzikirPagi(): Flowable<List<DzikirPagiResponseItem>>

    @GET("dzikir-petang")
    fun getDzikirPetang(): Flowable<List<DzikirPetangResponseItem>>

}