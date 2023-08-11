package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.model.CardsBy
import com.example.hearthstoneapp.util.BaseCoroutineTest
import com.example.hearthstoneapp.util.getOrAwaitValue
import com.example.heartstone_repository.data.HearthStoneRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CardsByViewModelImplTest : BaseCoroutineTest() {

    private lateinit var viewModel: CardsByViewModelImpl

    private val cardsByState = MediatorLiveData<CardsByViewModel.CardsByState>()

    private lateinit var getCardsByUseCase: GetCardsByUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    private val mockCardsBy = listOf(CardsBy("teste", "teste.com"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCardsByUseCase = GetCardsByUseCase(mockRepo)
        viewModel = CardsByViewModelImpl(cardsByState, getCardsByUseCase)
    }

    @Test
    fun `when onGetCardsBy receives OnError, post ShowError state`() = runTest {
        val errorMessage = "Error occurred"
        viewModel.onGetCardsBy(GetCardsByUseCase.Result.OnError(errorMessage))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is CardsByViewModel.CardsByState.ShowError)
        assertEquals(errorMessage, (state as CardsByViewModel.CardsByState.ShowError).message)
    }

    @Test
    fun `when onGetCardsBy receives OnSuccess, post CardsByLoaded state`() = runTest {
        viewModel.onGetCardsBy(GetCardsByUseCase.Result.OnSuccess(mockCardsBy))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is CardsByViewModel.CardsByState.CardsByLoaded)
        assertEquals(mockCardsBy, (state as CardsByViewModel.CardsByState.CardsByLoaded).data)
    }
}
