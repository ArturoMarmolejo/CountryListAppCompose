package com.arturomarmolejo.countrylistappcompose.data.network

import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface CountryServiceApi {

    @GET(COUNTRIES)
    suspend fun getAllCountries(): Response<List<CountryAPIResponseItem>>

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        const val COUNTRIES = "countries.json"
    }
}