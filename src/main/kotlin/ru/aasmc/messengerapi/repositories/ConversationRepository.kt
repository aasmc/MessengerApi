package ru.aasmc.messengerapi.repositories

import org.springframework.data.repository.CrudRepository
import ru.aasmc.messengerapi.models.Conversation

interface ConversationRepository : CrudRepository<Conversation, Long> {
    fun findBySenderId(id: Long): List<Conversation>

    fun findByRecipientId(id: Long): List<Conversation>

    fun findBySenderIdAndRecipientId(senderId: Long, recipientId: Long): Conversation?
}