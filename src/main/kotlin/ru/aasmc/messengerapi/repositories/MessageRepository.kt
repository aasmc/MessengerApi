package ru.aasmc.messengerapi.repositories

import org.springframework.data.repository.CrudRepository
import ru.aasmc.messengerapi.models.Message

interface MessageRepository : CrudRepository<Message, Long> {
    fun findByConversationId(conversationId: Long): List<Message>
}