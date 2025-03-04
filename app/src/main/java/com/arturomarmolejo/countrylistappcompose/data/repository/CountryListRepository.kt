package com.arturomarmolejo.countrylistappcompose.data.repository

import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * [CountryListRepository] -
 * Defines the methods to get the response from the API asynchronously using suspend functions
 */
interface CountryListRepository {
    suspend fun getAllCountries(): Response<List<CountryAPIResponseItem>>
}