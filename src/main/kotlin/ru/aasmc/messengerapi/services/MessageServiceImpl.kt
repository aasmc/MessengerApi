package ru.aasmc.messengerapi.services

import org.springframework.stereotype.Service
import ru.aasmc.messengerapi.exceptions.MessageEmptyException
import ru.aasmc.messengerapi.exceptions.MessageRecipientInvalidException
import ru.aasmc.messengerapi.models.Conversation
import ru.aasmc.messengerapi.models.Message
import ru.aasmc.messengerapi.models.User
import ru.aasmc.messengerapi.repositories.ConversationRepository
import ru.aasmc.messengerapi.repositories.MessageRepository
import ru.aasmc.messengerapi.repositories.UserRepository
import kotlin.jvm.Throws

@Service
class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val conversationService: ConversationService,
    val userRepository: UserRepository
) : MessageService {
    @Throws(
        MessageEmptyException::class,
        MessageRecipientInvalidException::class
    )
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)
        if (optional.isPresent) {
            val recipient = optional.get()
            if (messageText.isNotEmpty()) {
                val conversation: Conversation = if (
                    conversationService.conversationExists(sender, recipient)
                ) {
                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(
                    sender = sender,
                    recipient = recipient,
                    body = messageText,
                    conversation = conversation
                )
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }
        throw MessageEmptyException()
    }
}