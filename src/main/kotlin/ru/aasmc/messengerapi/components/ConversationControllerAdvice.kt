package ru.aasmc.messengerapi.components

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.aasmc.messengerapi.constants.ErrorResponse
import ru.aasmc.messengerapi.exceptions.ConversationIdInvalidException

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler(ConversationIdInvalidException::class)
    fun conversationIdInvalidException(
        conversationIdInvalidException: ConversationIdInvalidException
    ): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}