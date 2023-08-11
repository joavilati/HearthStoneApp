package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.CardsBy
import com.example.hearthstoneapp.util.BaseCoroutineTest
import com.example.hearthstoneapp.util.getOrAwaitValue
import com.example.heartstone_repository.data.HearthStoneRepository
import com.example.heartstone_repository.model.CardsByItemResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class GetCardsByUseCaseTest : BaseCoroutineTest() {

    private lateinit var useCase: GetCardsByUseCase

    @Mock
    private lateinit var mockRepo: HearthStoneRepository

    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)

        useCase = GetCardsByUseCase(mockRepo)
    }

    @Test
    fun `when repo returns data, emit OnSuccess`() = runTest {

        val mockedCardsBy = listOf(
            CardsBy(
                name = "name",
                image = "url",
                description = "description"
            )
        )
        val mockData = listOf(
            CardsByItemResponse(
                cardBackId = 123,
                text = "description",
                enabled = true,
                img = "url",
                imgAnimated = "",
                locale = "enUS",
                name = "name",
                sortCategory = 1,
                sortOrder = 2,
                source = ""
            )
        )

        val typeName = "typeNameExample"
        val name = "nameExample"

        Mockito.`when`(mockRepo.getCardsBy(typeName, name)).thenReturn(mockData)

        useCase.setParams(typeName, name).execute()

        val result = useCase.getLiveData().getOrAwaitValue()
        val isSuccess = result is GetCardsByUseCase.Result.OnSuccess
        assertTrue(isSuccess)
        if (isSuccess) {
            result as GetCardsByUseCase.Result.OnSuccess
            assertEquals(mockedCardsBy, result.data)
        }
    }

    @Test
    fun `when repo throws exception, emit OnError`() = runTest {
        // Given
        val errorMessage = "Error occurred"
        val typeName = "typeNameExample"
        val name = "nameExample"

        Mockito.`when`(mockRepo.getCardsBy(typeName, name))
            .thenThrow(RuntimeException(errorMessage))

        // When
        useCase.setParams(typeName, name).execute()

        // Then
        val result = useCase.getLiveData().getOrAwaitValue()
        assertTrue(result is GetCardsByUseCase.Result.OnError)
        assertEquals(errorMessage, (result as GetCardsByUseCase.Result.OnError).message)
    }
}
