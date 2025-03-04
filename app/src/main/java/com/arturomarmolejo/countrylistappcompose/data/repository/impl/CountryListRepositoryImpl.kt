package com.arturomarmolejo.countrylistappcompose.data.repository.impl

import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.data.network.CountryServiceApi
import com.arturomarmolejo.countrylistappcompose.data.repository.CountryListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

/**
 * [CountryRepositoryImpl] -
 * Implementation of [CountryRepository] interface
 * @param countryServiceApi defines the API interface to be called in order to get the response
 * since it is an operation to be performed in the background
 */
class CountryListRepositoryImpl @Inject constructor(
    private val countryServiceApi: CountryServiceApi,
): CountryListRepository {

    override suspend fun getAllCountries(): Response<List<CountryAPIResponseItem>> =
        countryServiceApi.getAllCountries()
}