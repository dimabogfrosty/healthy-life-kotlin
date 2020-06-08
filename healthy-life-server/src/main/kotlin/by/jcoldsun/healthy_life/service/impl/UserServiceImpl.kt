package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.UserNotFoundException
import by.jcoldsun.healthy_life.repository.RoleRepository
import by.jcoldsun.healthy_life.repository.UserRepository
import by.jcoldsun.healthy_life.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(private val userRepository: UserRepository,
                      private val roleRepository: RoleRepository) : UserService {
    override fun getByUsername(username: String) = userRepository.findByUsername(username)
            ?: throw UserNotFoundException("User with username = $username does not exist")

    override fun getByEmail(email: String) = userRepository.findByEmail(email)
            ?: throw UserNotFoundException("User with email = $email does not exist")

    override fun getAll(): MutableList<User> = userRepository.findAll()

    override fun getById(id: Long): User = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("User with id = $id does not exist") }

    @Transactional
    override fun save(entity: User): User {
        if (entity.roles.isEmpty()) {
            entity.roles = arrayListOf(roleRepository.findByName("USER"))
        }
        return userRepository.saveAndFlush(entity)
    }

    @Transactional
    override fun delete(id: Long) = userRepository.delete(getById(id))

    override fun loadUserByUsername(username: String?) = username?.let { getByUsername(it) }

}