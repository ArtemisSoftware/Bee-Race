package com.artemissoftware.beerace.feature.race.data.network.di

import com.artemissoftware.beerace.feature.race.data.network.BeeceptorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BeeceptorApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBeeceptorApi(retrofit: Retrofit): BeeceptorApi {
        return retrofit
            .create(BeeceptorApi::class.java)
    }
}