package ru.aasmc.messengerapi.services

import org.springframework.stereotype.Service
import ru.aasmc.messengerapi.exceptions.InvalidUserIdException
import ru.aasmc.messengerapi.exceptions.UsernameUnavailableException
import ru.aasmc.messengerapi.models.User
import ru.aasmc.messengerapi.repositories.UserRepository

@Service
class UserServiceImpl(val repository: UserRepository) : UserService {
    @Throws(UsernameUnavailableException::class)
    override fun attemptRegistration(userDetails: User): User {
        if (!usernameExists(userDetails.username)) {
            val user = User()
            user.username = userDetails.username
            user.phoneNumber = userDetails.phoneNumber
            user.password = userDetails.password
            repository.save(user)
            obscurePassword(user)
            return user
        }
        throw UsernameUnavailableException("The username ${userDetails.username} is unavailable")
    }

    override fun listUsers(currentUser: User): List<User> {
        return repository.findAll().mapTo(ArrayList()) { it }
            .filter { it != currentUser }
    }

    override fun retrieveUserData(username: String): User? {
        val user = repository.findByUsername(username)
        obscurePassword(user)
        return user
    }

    @Throws(InvalidUserIdException::class)
    override fun retrieveUserData(id: Long): User? {
        val userOptional = repository.findById(id)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            obscurePassword(user)
            return user
        }
        throw InvalidUserIdException("A user with an id of '$id' does not exist.")
    }

    override fun usernameExists(username: String): Boolean {
        return repository.findByUsername(username) != null
    }

    private fun obscurePassword(user: User?) {
        user?.password = "XXX XXXX XXX"
    }
}