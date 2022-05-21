package ru.aasmc.messengerapi.components

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.aasmc.messengerapi.constants.ErrorResponse
import ru.aasmc.messengerapi.constants.ResponseConstants
import ru.aasmc.messengerapi.exceptions.MessageEmptyException
import ru.aasmc.messengerapi.exceptions.MessageRecipientInvalidException

@ControllerAdvice
class MessageControllerAdvice {
    @ExceptionHandler(MessageEmptyException::class)
    fun messageEmpty(
        messageEmptyException: MessageEmptyException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.MESSAGE_EMPTY.value,
            messageEmptyException.message
        )
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
    fun messageRecipientInvalid(
        messageRecipientInvalidException: MessageRecipientInvalidException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.MESSAGE_RECIPIENT_INVALID.value,
            messageRecipientInvalidException.message
        )
        return ResponseEntity.unprocessableEntity().body(res)
    }
}