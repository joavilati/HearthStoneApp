package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.util.BaseCoroutineTest
import com.example.hearthstoneapp.util.getOrAwaitValue
import com.example.heartstone_repository.repository.HearthStoneRepository
import com.example.heartstone_repository.model.CardResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class GetSingleCardUseCaseTest: BaseCoroutineTest() {

    private lateinit var useCase: GetSingleCardUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)

        useCase = GetSingleCardUseCase(mockRepo)
    }

    @Test
    fun `when repo returns data, emit OnSuccess`()  = runTest {

        val mockData = listOf(CardResponse(
            armor = "",
            artist = "",
            attack = 5,
            cardId = "123",
            cardSet = "Classic",
            classes = listOf("Mage"),
            collectible = true,
            cost = 3,
            dbfId = 123,
            durability = 0,
            elite = false,
            faction = "Neutral",
            flavor = "Some flavor text",
            health = 5,
            howToGet = "",
            howToGetGold = "",
            img = "image",
            imgGold = "url",
            locale = "enUS",
            mechanics = emptyList(),
            multiClassGroup = "",
            name = "TestCard",
            playerClass = "Mage",
            race = "",
            rarity = "Common",
            text = "description",
            type = "Minion"
        ))

        val mockCard = Card(
            image = "image",
            name = "TestCard",
            flavor = "Some flavor text",
            set = "Classic",
            faction = "Neutral",
            rarity = "Common",
            attack = 5,
            cost = 3,
            health = 5,
            description = "description"
        )

        Mockito.`when`(mockRepo.getSingleCard("TestCard")).thenReturn(mockData)

        useCase.setParams("TestCard").execute()

        val result = useCase.getLiveData().getOrAwaitValue()
        val isSuccess = result is GetSingleCardUseCase.Result.OnSuccess
        assertTrue(isSuccess)
        if(isSuccess) {
            result as GetSingleCardUseCase.Result.OnSuccess
            assertEquals(mockCard, result.data)
            return@runTest
        }
    }

    @Test
    fun `when repo throws exception, emit OnError`() = runTest {
        val errorMessage = "Error occurred"
        Mockito.`when`(mockRepo.getSingleCard("TestCard")).thenThrow(RuntimeException(errorMessage))

        useCase.setParams("TestCard").execute()

        val result = useCase.getLiveData().getOrAwaitValue()
        assertTrue(result is GetSingleCardUseCase.Result.OnError)
        assertEquals(errorMessage, (result as GetSingleCardUseCase.Result.OnError).message)
    }
}
