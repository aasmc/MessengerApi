package ru.aasmc.messengerapi.components

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.aasmc.messengerapi.constants.ErrorResponse
import ru.aasmc.messengerapi.constants.ResponseConstants
import ru.aasmc.messengerapi.exceptions.InvalidUserIdException
import ru.aasmc.messengerapi.exceptions.UserStatusEmptyException
import ru.aasmc.messengerapi.exceptions.UsernameUnavailableException

@ControllerAdvice
class UserControllerAdvice {
    @ExceptionHandler(UsernameUnavailableException::class)
    fun usernameUnavailable(
        usernameUnavailableException: UsernameUnavailableException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.USERNAME_UNAVAILABLE.value,
            usernameUnavailableException.message
        )
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidId(
        invalidUserIdException: InvalidUserIdException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.INVALID_USER_ID.value,
            invalidUserIdException.message
        )
        return ResponseEntity.badRequest().body(res)
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun statusEmpty(
        userStatusEmptyException: UserStatusEmptyException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.EMPTY_STATUS.value,
            userStatusEmptyException.message
        )
        return ResponseEntity.unprocessableEntity().body(res)
    }
}