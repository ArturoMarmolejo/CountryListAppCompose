package com.arturomarmolejo.countrylistappcompose.di

import com.arturomarmolejo.countrylistappcompose.data.repository.CountryListRepository
import com.arturomarmolejo.countrylistappcompose.data.repository.impl.CountryListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsCountryListRepository(
        countryListRepositoryImpl: CountryListRepositoryImpl
    ): CountryListRepository
}