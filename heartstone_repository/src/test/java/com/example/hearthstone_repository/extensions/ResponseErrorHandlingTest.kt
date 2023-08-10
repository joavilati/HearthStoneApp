package com.example.hearthstone_repository.extensions

import com.example.heartstone_repository.utils.*
import com.example.heartstone_repository.utils.extensions.handleErrors
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class ResponseErrorHandlingTest {

    @Test(expected = BadRequestException::class)
    fun `should throw BadRequestException for 400 response code`() {
        val mockResponse: Response<Any> = Mockito.mock(Response::class.java) as Response<Any>
        Mockito.`when`(mockResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockResponse.code()).thenReturn(400)

        mockResponse.handleErrors()
    }

    @Test(expected = UnauthorizedException::class)
    fun `should throw UnauthorizedException for 401 response code`() {
        val mockResponse: Response<Any> = Mockito.mock(Response::class.java) as Response<Any>
        Mockito.`when`(mockResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockResponse.code()).thenReturn(401)

        mockResponse.handleErrors()
    }

    @Test(expected = ForbiddenException::class)
    fun `should throw ForbiddenException for 403 response code`() {
        val mockResponse: Response<Any> = Mockito.mock(Response::class.java) as Response<Any>
        Mockito.`when`(mockResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockResponse.code()).thenReturn(403)

        mockResponse.handleErrors()
    }

    @Test(expected = NotFoundException::class)
    fun `should throw NotFoundException for 404 response code`() {
        val mockResponse: Response<Any> = Mockito.mock(Response::class.java) as Response<Any>
        Mockito.`when`(mockResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockResponse.code()).thenReturn(404)

        mockResponse.handleErrors()
    }

    @Test(expected = InternalServerErrorException::class)
    fun `should throw InternalServerErrorException for 500 response code`() {
        val mockResponse: Response<Any> = Mockito.mock(Response::class.java) as Response<Any>
        Mockito.`when`(mockResponse.isSuccessful).thenReturn(false)
        Mockito.`when`(mockResponse.code()).thenReturn(500)

        mockResponse.handleErrors()
    }
}
