package org.crolopez.sharedexpense.shared.infrastructure.api.controllers

import io.micronaut.http.*
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.crolopez.sharedexpense.shared.application.exception.InvalidAmountException
import org.crolopez.sharedexpense.shared.application.exception.UserCannotBeAddedException
import org.crolopez.sharedexpense.shared.application.exception.UserDoesNotExistException
import org.crolopez.sharedexpense.shared.application.exception.UserNotInGroupException
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ErrorCodeEnum
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ErrorDto
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.ResponseDto
import javax.inject.Singleton

@Singleton
class GlobalExceptionHandler : ExceptionHandler<Exception, MutableHttpResponse<ResponseDto<String>>> {

    override fun handle(request: HttpRequest<*>, exception: Exception): MutableHttpResponse<ResponseDto<String>> {
        return getResponseFromException(exception)
    }

    private fun getResponseFromException(exception: Exception): MutableHttpResponse<ResponseDto<String>> {
        var code: ErrorCodeEnum
        var title: String
        var httpStatus: HttpStatus

        when (exception) {
            is UserNotInGroupException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                code = ErrorCodeEnum.USER_NOT_IN_GROUP
                title = exception.message.toString()
            }
            is UserDoesNotExistException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                code = ErrorCodeEnum.USER_DOES_NOT_EXIST
                title = exception.message.toString()
            }
            is UserCannotBeAddedException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                code = ErrorCodeEnum.USER_CANNOT_BE_ADDED
                title = exception.message.toString()
            }
            is InvalidAmountException -> {
                httpStatus = HttpStatus.BAD_REQUEST
                code = ErrorCodeEnum.INVALID_AMOUNT
                title = exception.message.toString()
            }
            else -> {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
                code = ErrorCodeEnum.UNEXPECTED
                title = exception.message.toString()
            }
        }

        val response = ResponseDto<String>(
                data = null,
                errors = listOf(ErrorDto(
                    code = code,
                    title = title
                )
            )
        )

        val responseFactory: HttpResponseFactory = HttpResponseFactory.INSTANCE
        return responseFactory.status(httpStatus, response)
    }
}