package com.viavarejo.desafio.android.tawane.hq.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.viavarejo.desafio.android.tawane.hq.model.CharacterAllResponse
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResponse
import com.viavarejo.desafio.android.tawane.hq.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
internal class HomeViewModelTest {
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var repository: CharacterRepository

    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        homeViewModel = HomeViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loading() = TestCoroutineDispatcher().runBlockingTest {
        val expect = listOf(HomeViewModel.ScreenState.Loading)
        val actual = mutableListOf<HomeViewModel.ScreenState>()

        homeViewModel.state.observeForever {
            actual.add(it)
        }

        homeViewModel.getCharacters()
        Assert.assertEquals(expect, actual)
    }


    @Test
    fun apiUse() = TestCoroutineDispatcher().runBlockingTest {
        val expect = listOf(HomeViewModel.ScreenState.Loading)
        val actual = mutableListOf<HomeViewModel.ScreenState>()

        Mockito.`when`(repository.getCharacter()).thenReturn(Response.success(CharacterResponse(
            CharacterAllResponse(offset = 11, total = 11, results = arrayListOf(), limit = 2, count = 2)
        )))

        homeViewModel.state.observeForever {
            actual.add(it)
        }

        homeViewModel.getCharacters()
        Assert.assertEquals(expect, actual)
    }

}