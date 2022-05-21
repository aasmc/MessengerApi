package ru.aasmc.messengerapi.services

import ru.aasmc.messengerapi.models.Message
import ru.aasmc.messengerapi.models.User

interface MessageService {
    fun sendMessage(
        sender: User, recipientId: Long,
        messageText: String
    ): Message
}