package com.arturomarmolejo.countrylistappcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.data.repository.CountryListRepository
import com.arturomarmolejo.countrylistappcompose.domain.GetAllCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [CountryListViewModel] -
 * Defines the ViewModel for the application
 * Contains the information that may be shared between different composable functions
 * @param getAllCountriesUseCase defines the usecase to be used by the ViewModel in order to retrieve
 * data from the domain layer
 */
@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
): ViewModel() {
    private val _allCountries: MutableStateFlow<UIState<List<CountryAPIResponseItem>>> = MutableStateFlow(UIState.LOADING)
    val allCountries: StateFlow<UIState<List<CountryAPIResponseItem>>> get() = _allCountries

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch {
            getAllCountriesUseCase().collect {
                _allCountries.value = it
            }
        }
    }
}