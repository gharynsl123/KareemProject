package com.example.finalproject.data.network

import com.example.finalproject.BuildConfig
import com.example.finalproject.BuildConfig.BASE_URL
import com.example.finalproject.BuildConfig.QURAN_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientPagi {
    fun getApiService(): ApiService {
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val okHttpClient = OkHttpClient.Builder().retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor).addInterceptor(defaultHttpClient())
            .pingInterval(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    private fun defaultHttpClient(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            return@Interceptor it.proceed(request)
        }
    }
}