package com.imkryp70n.kryp70nnime.data

import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author Eduardo Medina
 */
object ApiClient {

    private const val API_BASE_URL = "https://api.consumet.org/"

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(): ServicesApiInterface? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {

        @GET("anime/gogoanime/recent-episodes")
        fun discoverAnime(): Call <DiscoveryModel>

        @GET("anime/gogoanime/top-airing")
        fun topAiringAnime(): Call <TrendingModel>
    }
}