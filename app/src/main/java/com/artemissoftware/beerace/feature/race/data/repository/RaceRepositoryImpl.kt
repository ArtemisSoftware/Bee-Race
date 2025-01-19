package com.artemissoftware.beerace.feature.race.data.repository

import com.artemissoftware.beerace.core.data.remote.HandleNetwork
import com.artemissoftware.beerace.feature.race.data.mapper.toDuration
import com.artemissoftware.beerace.feature.race.data.mapper.toRacer
import com.artemissoftware.beerace.feature.race.data.network.source.BeeceptorApiSource
import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.core.domain.error.DataError
import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.models.RaceOverview
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.feature.race.domain.repository.RaceRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class RaceRepositoryImpl(
    private val beeceptorApiSource: BeeceptorApiSource
): RaceRepository {
    override suspend fun getRaceOverview(): Resource<RaceOverview> {
        return coroutineScope {

            val deferredDuration = async { getDuration() }
            val deferredStatus = async { getRacersPosition() }

            val resultDuration = deferredDuration.await()
            val resultStatus = deferredStatus.await()

            when{
                resultDuration is Resource.Success && resultStatus is Resource.Success ->{
                    Resource.Success(
                        RaceOverview(
                            raceDuration = resultDuration.data,
                            racers = resultStatus.data
                        )
                    )
                }
                resultDuration is Resource.Failure -> {
                    Resource.Failure(error = resultDuration.error)
                }
                resultStatus is Resource.Failure -> {
                    Resource.Failure(error = resultStatus.error)
                }
                else -> {
                    Resource.Failure(error = DataError.NetworkError.Unknown)
                }
            }
        }
    }

    private suspend fun getDuration(): Resource<RaceDuration> {
        return HandleNetwork.safeNetworkCall {
            beeceptorApiSource.getDuration().toDuration()
        }
    }

    override suspend fun getRacersPosition(): Resource<List<Racer>> {
        return HandleNetwork.safeNetworkCall {
            beeceptorApiSource.getRaceStatus().bees.map{ it.toRacer() }
        }
    }
}