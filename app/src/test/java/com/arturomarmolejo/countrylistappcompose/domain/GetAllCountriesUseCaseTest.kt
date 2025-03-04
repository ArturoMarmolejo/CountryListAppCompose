package com.arturomarmolejo.countrylistappcompose.domain

import com.arturomarmolejo.countrylistappcompose.core.UIState
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.data.repository.CountryListRepository
import com.arturomarmolejo.countrylistappcompose.data.repository.impl.CountryListRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllCountriesUseCaseTest {

    private lateinit var countryListUseCase: GetAllCountriesUseCase
    private lateinit var repository: CountryListRepository


    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        repository = mockk(relaxed = true)
        countryListUseCase = GetAllCountriesUseCase(repository, dispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllCountriesUseCase emits LOADING state first`() = runTest {
        val flow = countryListUseCase.invoke().first()
        assertEquals(UIState.LOADING, flow)
    }

    @Test
    fun `getAllCountriesUseCase emits SUCCESS state with data when response is successful`() = runTest {
        //Arrange
        val countryList = listOf(
            CountryAPIResponseItem(name = "Argentina", capital = "Buenos Aires"),
            CountryAPIResponseItem(name = "Mexico", capital = "Mexico City")
        )

        val response = Response.success(countryList)
        coEvery { repository.getAllCountries() } returns response

        // Act
        val flow = countryListUseCase()
        val emissions = flow.toList()

        //Assert
        assertEquals(2, emissions.size)
        assertEquals(UIState.LOADING, emissions[0])
        assertEquals(UIState.SUCCESS(countryList), emissions[1])
    }

    @Test
    fun `getAllCountriesUseCase emits ERROR state with data when response is NOT successful`() = runTest {
        //Arrange
        val errorMessage = "Error message"
        val response = Response.error<List<CountryAPIResponseItem>>(400, errorMessage.toResponseBody())

        coEvery { repository.getAllCountries() } returns response

        // Act
        val flow = countryListUseCase()
        val emissions = flow.toList()

        //Assert
        assertEquals(2, emissions.size)
        assertEquals(UIState.LOADING, emissions[0])
        assertTrue(emissions[1] is UIState.ERROR)
    }

    @Test
    fun `getAllCountriesUseCase emits ERROR state with data when response is NULL`() = runTest {
        //Arrange
        val errorMessage = "Response from server is null"

        coEvery { repository.getAllCountries() } returns Response.success(null)

        // Act
        val flow = countryListUseCase()
        val emissions = flow.toList()

        //Assert
        assertEquals(2, emissions.size)
        assertEquals(UIState.LOADING, emissions[0])
        assertTrue(emissions[1] is UIState.ERROR)
        assertEquals(errorMessage, (emissions[1] as UIState.ERROR).error.message)
    }


}