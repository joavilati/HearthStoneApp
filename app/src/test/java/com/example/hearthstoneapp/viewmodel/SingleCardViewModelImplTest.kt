package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetSingleCardUseCase
import com.example.hearthstoneapp.model.Card
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
class SingleCardViewModelImplTest : BaseCoroutineTest() {

    private lateinit var viewModel: SingleCardViewModelImpl

    private val singleCardState = MediatorLiveData<SingleCardViewModel.SingleCardState>()

    private lateinit var getSingleCardUseCase: GetSingleCardUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    private val mockCard = Card(
        image = "some_url",
        name = "TestCard",
        flavor = "Some flavor text",
        set = "Classic",
        faction = "Neutral",
        rarity = "Common",
        attack = 5,
        cost = 3,
        health = 5
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getSingleCardUseCase = GetSingleCardUseCase(mockRepo)
        viewModel = SingleCardViewModelImpl(singleCardState, getSingleCardUseCase)
    }

    @Test
    fun `when onGetSingleCard receives OnError, post ShowError state`() = runTest {
        val errorMessage = "Error occurred"
        viewModel.onGetSingleCard(GetSingleCardUseCase.Result.OnError(errorMessage))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is SingleCardViewModel.SingleCardState.ShowError)
        assertEquals(errorMessage, (state as SingleCardViewModel.SingleCardState.ShowError).message)
    }

    @Test
    fun `when onGetSingleCard receives OnSuccess, post SingleCardLoaded state`() = runTest {
        viewModel.onGetSingleCard(GetSingleCardUseCase.Result.OnSuccess(mockCard))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is SingleCardViewModel.SingleCardState.SingleCardLoaded)
        assertEquals(mockCard, (state as SingleCardViewModel.SingleCardState.SingleCardLoaded).data)
    }
}
