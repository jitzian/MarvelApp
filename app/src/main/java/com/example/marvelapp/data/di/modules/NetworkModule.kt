package com.example.marvelapp.data.di.modules

import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.constants.GlobalConstants.Companion.MARVEL_PRIVATE_KEY
import com.example.marvelapp.constants.GlobalConstants.Companion.MARVEL_PUBLIC_KEY
import com.example.marvelapp.data.remote.RestApi
import com.example.marvelapp.utils.generateHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Scope of the injected component
class NetworkModule {

    @Provides
    @Singleton // Scope of the instance
    fun providesRetrofit(): RestApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(QueryInterceptor()).build()

        return Retrofit.Builder().baseUrl(GlobalConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RestApi::class.java)
    }
}

private class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, MARVEL_PRIVATE_KEY, MARVEL_PUBLIC_KEY)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", MARVEL_PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString()).addQueryParameter("hash", hash).build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }

}