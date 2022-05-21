package ru.aasmc.messengerapi.components

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.aasmc.messengerapi.constants.ErrorResponse
import ru.aasmc.messengerapi.constants.ResponseConstants
import ru.aasmc.messengerapi.exceptions.UserDeactivatedException

@ControllerAdvice
class RestControllerAdvice {
    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(
        userDeactivatedException: UserDeactivatedException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.ACCOUNT_DEACTIVATED.value,
            userDeactivatedException.message
        )
        // HTTP error 403 - failed to authorize
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)
    }
}