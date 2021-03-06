package ru.aasmc.messengerapi.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
class Conversation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @DateTimeFormat
    val createdAt: Date = Date.from(Instant.now()),
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    var sender: User? = null,
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    var recipient: User? = null
) {
    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    private var messages: Collection<Message>? = null
}