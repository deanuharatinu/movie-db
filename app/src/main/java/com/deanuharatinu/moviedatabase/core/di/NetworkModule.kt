package com.deanuharatinu.moviedatabase.core.di

import com.deanuharatinu.moviedatabase.BuildConfig
import com.deanuharatinu.moviedatabase.core.utils.ApiConstants.BASE_ENDPOINT
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    return httpLoggingInterceptor
  }

  @Provides
  fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build()
  }

  @Provides
  fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_ENDPOINT)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(client)
      .build()
  }

  @Provides
  fun createMoshi(): Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
  }
}