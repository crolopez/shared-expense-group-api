package org.crolopez.sharedexpense.ut.shared.infrastructure.api.controllers


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponseFactory
import io.micronaut.http.HttpStatus
import org.crolopez.sharedexpense.group.application.services.GroupServiceImpl
import org.crolopez.sharedexpense.shared.application.exception.InvalidAmountException
import org.crolopez.sharedexpense.shared.application.exception.UserCannotBeAddedException
import org.crolopez.sharedexpense.shared.application.exception.UserDoesNotExistException
import org.crolopez.sharedexpense.shared.application.exception.UserNotInGroupException
import org.crolopez.sharedexpense.shared.infrastructure.api.controllers.GlobalExceptionHandler
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ErrorCodeEnum
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ErrorDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ResponseDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GlobalExceptionHandlerUnitTest {

    @Mock
    private lateinit var responseFactory: HttpResponseFactory

    @InjectMocks
    private lateinit var globalExceptionHandler: GlobalExceptionHandler

    @Test
    fun givenUserNotInGroupException_whenHandle_thenReturnErrorResponse() {
        val groupId: Long = 2482
        val username = "fakeUser"
        val exception = UserNotInGroupException(groupId, username)

        val response = globalExceptionHandler.handle(mock(HttpRequest::class.java), exception)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertEquals(
            listOf(ErrorDto(ErrorCodeEnum.USER_NOT_IN_GROUP, "User $username is not in $groupId group")),
            response.body.get().errors
        )
    }

    @Test
    fun givenUserDoesNotExistException_whenHandle_thenReturnErrorResponse() {
        val username = "fakeUser"
        val exception = UserDoesNotExistException(username)

        val response = globalExceptionHandler.handle(mock(HttpRequest::class.java), exception)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertEquals(
            listOf(ErrorDto(ErrorCodeEnum.USER_DOES_NOT_EXIST, "User $username does not exist")),
            response.body.get().errors
        )
    }

    @Test
    fun givenUserCannotBeAddedException_whenHandle_thenReturnErrorResponse() {
        val username = "fakeUser"
        val groupId: Long = 318412
        val exception = UserCannotBeAddedException(groupId, username)

        val response = globalExceptionHandler.handle(mock(HttpRequest::class.java), exception)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertEquals(
            listOf(ErrorDto(ErrorCodeEnum.USER_CANNOT_BE_ADDED,  "User $username cannot be added to $groupId group")),
            response.body.get().errors
        )
    }

    @Test
    fun givenInvalidAmountException_whenHandle_thenReturnErrorResponse() {
        val amount = -234.22
        val exception = InvalidAmountException(amount)

        val response = globalExceptionHandler.handle(mock(HttpRequest::class.java), exception)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertEquals(
            listOf(ErrorDto(ErrorCodeEnum.INVALID_AMOUNT, "Amount -234.22 is not valid. Must be greater than 0")),
            response.body.get().errors
        )
    }

    @Test
    fun givenUnexpectedException_whenHandle_thenReturnErrorResponse() {
        val exception = RuntimeException("Unexpected exception")
        val handler = GlobalExceptionHandler()

        val response = handler.handle(mock(HttpRequest::class.java), exception)

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.status)
        assertEquals(
            listOf(ErrorDto(ErrorCodeEnum.UNEXPECTED, "Unexpected exception")),
            response.body.get().errors
        )
    }
}
