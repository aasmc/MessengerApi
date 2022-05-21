package ru.aasmc.messengerapi.repositories

import org.springframework.data.repository.CrudRepository
import ru.aasmc.messengerapi.models.User

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}