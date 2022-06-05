package com.example.finalproject.data.network.quran

import com.example.finalproject.data.response.quranres.QuranResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface QuranApiService {

    @GET("quran-uthmani")
    fun getQuran(): Flowable<QuranResponse>

    @GET("search")
    fun searchQuranByQuery(query: String): Flowable<QuranResponse>
}