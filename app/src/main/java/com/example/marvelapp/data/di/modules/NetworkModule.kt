package com.example.marvelapp.data.di.modules

import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.utils.generateHash
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(QueryInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl(GlobalConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

private class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        //val hash = generateHash(ts, BuildConfig.MARVEL_PRIVATE_KEY, BuildConfig.MARVEL_PUBLIC_KEY)

        val url = originalUrl.newBuilder()
            //.addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            //.addQueryParameter("MARVEL_PUBLIC_KEY", System.getenv("MARVEL_PUBLIC_KEY"))
            .addQueryParameter("apikey", "28a0bd1762195c2c87c2733f32b39076")
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}