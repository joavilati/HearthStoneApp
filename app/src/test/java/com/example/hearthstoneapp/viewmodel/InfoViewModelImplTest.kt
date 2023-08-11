package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetInfoUseCase
import com.example.hearthstoneapp.model.*
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
class InfoViewModelImplTest : BaseCoroutineTest() {
    //
    private lateinit var viewModel: InfoViewModelImpl

    private val infoState = MediatorLiveData<InfoViewModel.InfoState>()

    private lateinit var getInfoUseCase: GetInfoUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    private val mockInfo = Info(
        patch = "3.0.0.9786",
        classes = listOf(Classes.DRUID, Classes.HUNTER, Classes.MAGE),
        sets = listOf(Sets.BASIC, Sets.CLASSIC, Sets.CREDITS),
        types = listOf(Types.HERO, Types.MINION, Types.SPELL),
        factions = listOf(Factions.HORDE, Factions.ALLIANCE, Factions.NEUTRAL),
        qualities = listOf(Qualities.FREE, Qualities.COMMON, Qualities.RARE),
        races = listOf(Races.DEMON, Races.DRAGON, Races.MECH),
        locales = listOf(Locales.DEDE, Locales.ENGB, Locales.ENUS)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getInfoUseCase = GetInfoUseCase(mockRepo)
        viewModel = InfoViewModelImpl(infoState, getInfoUseCase)
    }

    @Test
    fun `when onGetInfo receives OnError, post ShowError state`() = runTest {
        val errorMessage = "Error occurred"
        viewModel.onGetInfo(GetInfoUseCase.Result.OnError(errorMessage))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is InfoViewModel.InfoState.ShowError)
        assertEquals(errorMessage, (state as InfoViewModel.InfoState.ShowError).message)
    }

    @Test
    fun `when onGetInfo receives OnSuccess, post InfoLoaded state`() = runTest { // preencha com os valores mockados
        viewModel.onGetInfo(GetInfoUseCase.Result.OnSuccess(mockInfo))
        val state = viewModel.getState().getOrAwaitValue()
        assertTrue(state is InfoViewModel.InfoState.InfoLoaded)
        assertEquals(mockInfo, (state as InfoViewModel.InfoState.InfoLoaded).info)
    }
}
