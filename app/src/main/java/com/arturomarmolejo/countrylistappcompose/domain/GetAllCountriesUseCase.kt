package com.arturomarmolejo.countrylistappcompose.domain

import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.data.repository.CountryListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *  [GetAllCountriesUseCase] - Use case to get all countries from the API. Implementation
 *  of usecase functionality as per requirements for official App architecture principles.
 *  * @param coroutineDispatcher defines the thread to run the coroutine on. We use the IO thread
 */
class GetAllCountriesUseCase @Inject constructor(
    private val countryListRepository: CountryListRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<UIState<List<CountryAPIResponseItem>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = countryListRepository.getAllCountries()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("Response from server is null")
            } else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }.flowOn(dispatcher)

}