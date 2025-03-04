# Country App

This is a simpleAndroid application that displays a list of countries fetched from a public API.

## Features

* Displays a list of countries with their names, capitals, and region.
* Uses coroutines for asynchronous operations.
* Built with Jetpack Compose and current architectural patterns (official App/Clean Architecture)

## API

The app uses the following publicAPI to fetch the list of countries:

[https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json)

## Screenshots

<img src="https://github.com/ArturoMarmolejo/CountryListAppCompose/blob/master/app/src/main/res/drawable/screenshot_1.png" width="300">

## Architecture

The app follows a simple Model-View-ViewModel (MVVM) and Clean Architecture principles while using Jetpack Compose and Dependency Injection

* **Data:** Represents the country data.
* **Domain:** Contains the business logic in the form of a usecase for getting the list of countries
* **Presentation:** Displays the list of countries using a `LazyLayout`.


## Libraries

* Kotlin Coroutines
* Retrofit
* Jetpack Compose
* Viewmodel
* Lifecycle
* Junit and Mockk for Unit Testing
* Dagger Hilt for Dependency Injection

## How to run

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or device.

## Future improvements

* Implement a search feature to filter countries.
* Add detailed information screens for each country.
* Use Jetpack Navigation in case we have more screens to display
