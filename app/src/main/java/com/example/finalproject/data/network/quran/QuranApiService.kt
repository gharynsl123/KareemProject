package com.example.finalproject.data.network.quran

import androidx.lifecycle.LiveData
import com.example.finalproject.data.response.quranres.QuranResponse
import com.example.finalproject.data.response.quranres.SurahsItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.Flow

interface QuranApiService {

    @GET("quran")
    fun getQuran(): Flowable<QuranResponse>

    @GET("surah/{id}")
    fun searchQuranByQuery(
        @Path("id") query: String
    ): Flowable<QuranResponse>
}