package ru.aasmc.messengerapi.listeners

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import ru.aasmc.messengerapi.models.User
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class UserListener {
    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password)
    }
}