package com.example.weathersampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathersampleapp.data.repository.WeatherRepository
import com.example.weathersampleapp.view.activity.weatherhome.WeatherHomeViewModel

import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WeatherViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        //MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getWeatherApiSuccess() {
        val viewModel = WeatherHomeViewModel()
        viewModel.repository = WeatherRepository(MockWeatherService())

        TestCoroutineScope().launch {
            viewModel.getWeatherDetails()
            assertTrue(viewModel.apiRequestInProgress.value!!)
        }
        assertFalse(viewModel.apiRequestInProgress.value!!)
    }

    @Test
    fun getWeatherApiFailure() {
        val viewModel = WeatherHomeViewModel()
        viewModel.repository = WeatherRepository(MockWeatherService().apply {
            requireSuccessfulResponse = false
        })

        TestCoroutineScope().launch {
            viewModel.getWeatherDetails()
            assertTrue(viewModel.apiRequestInProgress.value!!)
        }
        assertFalse(viewModel.apiRequestInProgress.value!!)
        assertNotNull(viewModel.apiFailureEvent.value)
        assertEquals(400, viewModel.apiFailureEvent.value?.errorCode)

    }
}
