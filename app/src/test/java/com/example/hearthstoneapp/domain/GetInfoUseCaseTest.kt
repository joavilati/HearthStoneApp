package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.*
import com.example.hearthstoneapp.util.BaseCoroutineTest
import com.example.hearthstoneapp.util.getOrAwaitValue
import com.example.heartstone_repository.data.HearthStoneRepository
import com.example.heartstone_repository.model.InfoResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class GetInfoUseCaseTest: BaseCoroutineTest() {

    private lateinit var useCase: GetInfoUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)

        useCase = GetInfoUseCase(mockRepo)
    }


    @Test
    fun `when repo returns data, emit OnSuccess`()  = runTest {

        val mockData = InfoResponse(
            patch = "3.0.0.9786",
            classes = listOf("Druid", "Hunter", "Mage"),
            sets = listOf("Basic", "Classic", "Credits"),
            types = listOf("Hero", "Minion", "Spell"),
            factions = listOf("Horde", "Alliance", "Neutral"),
            qualities = listOf("Free", "Common", "Rare"),
            races = listOf("Demon", "Dragon", "Mech"),
            locales = listOf("deDE", "enGB", "enUS")
        )

        val mockInfo = Info(
            patch = "3.0.0.9786",
            classes = listOf(Classes.DRUID, Classes.HUNTER, Classes.MAGE),
            sets = listOf(Sets.BASIC, Sets.CLASSIC, Sets.CREDITS),
            types = listOf(Types.HERO, Types.MINION, Types.SPELL),
            factions = listOf(Factions.HORDE, Factions.ALLIANCE, Factions.NEUTRAL),
            qualities = listOf(Qualities.FREE, Qualities.COMMON, Qualities.RARE),
            races = listOf(Races.DEMON, Races.DRAGON, Races.MECH),
            locales = listOf(Locales.DEDE, Locales.ENGB, Locales.ENUS)
        )

        Mockito.`when`(mockRepo.getInfo()).thenReturn(mockData)

        useCase.execute()

        val result = useCase.getLiveData().getOrAwaitValue()
        val isSuccess = result is GetInfoUseCase.Result.OnSuccess
        assertTrue(isSuccess)
        if(isSuccess) {
            result as GetInfoUseCase.Result.OnSuccess
            assertEquals(mockInfo, result.data)
            return@runTest
        }
    }

    @Test
    fun `when repo throws exception, emit OnError`() = runTest {
        // Given
        val errorMessage = "Error occurred"
        Mockito.`when`(mockRepo.getInfo()).thenThrow(RuntimeException(errorMessage))

        // When
        useCase.execute()

        // Then
        val result = useCase.getLiveData().getOrAwaitValue()
        assertTrue(result is GetInfoUseCase.Result.OnError)
        assertEquals(errorMessage, (result as GetInfoUseCase.Result.OnError).message)
    }
}
