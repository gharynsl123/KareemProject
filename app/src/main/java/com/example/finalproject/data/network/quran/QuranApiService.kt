package com.example.finalproject.data.network.quran

import com.example.finalproject.data.response.quranres.QuranResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface QuranApiService {

    @GET("quran-uthmani")
    fun getQuran(): Flowable<QuranResponse>

    @GET("search")
    fun searchQuranByQuery(
        @Query("query") query: String
    ): Flowable<QuranResponse>

}