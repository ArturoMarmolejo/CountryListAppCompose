package com.arturomarmolejo.countrylistappcompose.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.presentation.viewmodel.CountryListViewModel

@Composable
fun CountryList(
    countryListViewModel: CountryListViewModel,
    modifier: Modifier = Modifier) {
    // Collection of the allCountries flow in a lifecycle aware manner.
    val countryListState = countryListViewModel.allCountries.collectAsStateWithLifecycle().value
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when(countryListState) {
            is UIState.LOADING -> {
                CircularProgressIndicator(
                    modifier = modifier
                )
            }
            is UIState.SUCCESS -> {
                LazyColumn {
                    itemsIndexed(
                        items = countryListState.response,
                        key = { index, country ->  index}
                    ) { index, country ->
                        CountryItem(country)
                    }
                }
            }
            is UIState.ERROR -> {
                Text(
                    color = MaterialTheme.colorScheme.error,
                    text = countryListState.error.message.toString()
                )
            }
        }
    }

}