package com.artemissoftware.beerace.feature.race.data.di

import com.artemissoftware.beerace.feature.race.data.repository.RaceRepositoryImpl
import com.artemissoftware.beerace.feature.race.data.network.source.BeeceptorApiSource
import com.artemissoftware.beerace.feature.race.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCountryClient(beeceptorApiSource: BeeceptorApiSource): RaceRepository {
        return RaceRepositoryImpl(beeceptorApiSource = beeceptorApiSource)
    }
}