package com.example.finalproject.data.network.quran

import com.example.finalproject.data.response.quranres.QuranResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {

    @GET("quran")
    fun getQuran(): Flowable<QuranResponse>

    @GET("surah/{id}")
    fun searchQuranByQuery(
        @Path("id") query: String
    ): Flowable<QuranResponse>
}