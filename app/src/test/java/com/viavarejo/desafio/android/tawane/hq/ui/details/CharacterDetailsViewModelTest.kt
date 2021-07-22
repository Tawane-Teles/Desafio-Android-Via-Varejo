package com.viavarejo.desafio.android.tawane.hq.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.viavarejo.desafio.android.tawane.hq.model.*
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

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi

internal class CharacterDetailsViewModelTest {
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @Mock
    private lateinit var repository: CharacterRepository

    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        characterDetailsViewModel = CharacterDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun getPosition() = TestCoroutineDispatcher().runBlockingTest {

        Mockito.`when`(repository.getPositionClick()).thenReturn(
            getMockPosition()
        )

        Mockito.`when`(repository.getDataHQ()).thenReturn(
            getMockHQS()
        )

        val expected = listOf(
            CharacterDetailsViewModel.ScreenState.GetPosition(getMockPosition(), getMockHQS())
        )

        val actual = mutableListOf<CharacterDetailsViewModel.ScreenState>()

        characterDetailsViewModel.state.observeForever {
            actual.add(it)
        }
        characterDetailsViewModel.getPosition()

        Assert.assertEquals(expected, actual)

    }


    @Test
    fun navigateToHome() = TestCoroutineDispatcher().runBlockingTest {
        val expected = mutableListOf(CharacterDetailsViewModel.ScreenState.NavigateToHome)

        val actual = mutableListOf<CharacterDetailsViewModel.ScreenState>()

        characterDetailsViewModel.state.observeForever {
            actual.add(it)
        }
        characterDetailsViewModel.navigateToHome()

        Assert.assertEquals(expected, actual)

    }

    fun getMockPosition(): CharacterResults{
        return   CharacterResults(
            characterId = 11,
            name = "Hero",
            description = "Teste",
            thumbnail = null,
            resourceURI = ""
        )
    }

    fun getMockHQS(): MarvelComicsResponse {
        return MarvelComicsResponse(
            heroData =
            MarvelComicsData(
                offset = 1,
                count = 1,
                limit = 1,
                results = listOf(
                    MarvelComics(
                        id = 1,
                        description = "",
                        price = null,
                        thumbnail = null,
                        title = ""
                    )
                ),
                total = 1
            )
        )
    }

}