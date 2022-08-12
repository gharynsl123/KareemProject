package com.example.finalproject.data.network

import com.example.finalproject.data.response.DzikirPagiResponseItem
import com.example.finalproject.data.response.DzikirPetangResponseItem
import com.example.finalproject.data.response.quranres.QuranResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Dzikir Service

    @GET("dzikir-pagi")
    fun getDzikirPagi(): Flowable<List<DzikirPagiResponseItem>>

    @GET("dzikir-petang")
    fun getDzikirPetang(): Flowable<List<DzikirPetangResponseItem>>

    //Quran Service

    @GET("quran")
    fun getQuran(): Flowable<QuranResponse>

    @GET("surah/{id}")
    fun searchQuranByQuery(
        @Path("id") query: String
    ): Flowable<QuranResponse>

}