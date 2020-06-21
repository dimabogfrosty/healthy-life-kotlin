package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.UserNotFoundException
import by.jcoldsun.healthy_life.repository.UserRepository
import by.jcoldsun.healthy_life.service.AchievementService
import by.jcoldsun.healthy_life.service.RoleService
import by.jcoldsun.healthy_life.service.UserService
import by.jcoldsun.healthy_life.service.model.UserPagination
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(private val userRepository: UserRepository,
                      private val roleService: RoleService,
                      private val achievementService: AchievementService) : UserService {
    override fun getByUsername(username: String) = userRepository.findByUsername(username)
            ?: throw UserNotFoundException("User with username = $username does not exist")

    override fun getByEmail(email: String) = userRepository.findByEmail(email)
            ?: throw UserNotFoundException("User with email = $email does not exist")

    override fun getAll(): MutableList<User> = userRepository.findAll()

    override fun getAllUserWithPagination(page: Int, size: Int) =
            UserPagination(getAll().size.toLong(), userRepository.findAll(PageRequest.of(page, size)).toList())

    @Transactional
    override fun addRole(userId: Long, roleId: Long): List<Role> {
        val role = roleService.getById(roleId)
        val user = this.getById(userId)
        user.addRole(role)
        this.save(user)
        return roleService.getRolesByUser(userId)
    }

    override fun addAchievement(userId: Long, achievementId: Long): List<Achievement> {
        val achievement = achievementService.getById(achievementId)
        val user = this.getById(userId)
        user.addAchievement(achievement)
        this.save(user)
        return achievementService.getUserAchievements(userId)
    }

    override fun getById(id: Long): User = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("User with id = $id does not exist") }

    @Transactional
    override fun save(entity: User): User {
        if (entity.roles.isEmpty()) {
            entity.roles = arrayListOf(roleService.getByName("USER"))
        }
        return userRepository.saveAndFlush(entity)
    }

    @Transactional
    override fun delete(id: Long) = userRepository.delete(getById(id))

    override fun loadUserByUsername(username: String?) = username?.let { getByUsername(it) }

}