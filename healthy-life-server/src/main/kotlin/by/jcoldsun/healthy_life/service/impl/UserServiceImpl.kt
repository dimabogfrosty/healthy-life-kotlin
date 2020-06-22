package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.UserNotFoundException
import by.jcoldsun.healthy_life.repository.UserRepository
import by.jcoldsun.healthy_life.service.AchievementService
import by.jcoldsun.healthy_life.service.RoleService
import by.jcoldsun.healthy_life.service.TrainingService
import by.jcoldsun.healthy_life.service.UserService
import by.jcoldsun.healthy_life.service.model.UserPagination
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(private val userRepository: UserRepository,
                      private val roleService: RoleService,
                      private val achievementService: AchievementService,
                      private val trainingService: TrainingService) : UserService {
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
        if (user.addRole(role)) {
            this.save(user)
            return roleService.getRolesByUser(userId)
        } else throw Exception("Bad request")
    }

    @Transactional
    override fun addAchievement(userId: Long, achievementId: Long): List<Achievement> {
        val achievement = achievementService.getById(achievementId)
        val user = this.getById(userId)
        if (user.addAchievement(achievement)) {
            this.save(user)
            return achievementService.getUserAchievements(userId)
        } else throw Exception("Bad request")
    }

    @Transactional
    override fun addTraining(userId: Long, trainingId: Long): List<Training> {
        val training = trainingService.getById(trainingId)
        val user = this.getById(userId)
        if (user.addTraining(training)) {
            this.save(user)
            return trainingService.getUserTrainings(userId)
        } else throw Exception("Bad request")
    }

    @Transactional
    override fun removeRole(userId: Long, roleId: Long): List<Role> {
        val role = roleService.getById(roleId)
        val user = this.getById(userId)
        if (user.removeRole(role)) {
            this.save(user)
            return roleService.getRolesByUser(userId)
        } else throw Exception("Bad request")
    }

    @Transactional
    override fun removeAchievement(userId: Long, achievementId: Long): List<Achievement> {
        val achievement = achievementService.getById(achievementId)
        val user = this.getById(userId)
        if (user.removeAchievement(achievement)) {
            this.save(user)
            return achievementService.getUserAchievements(userId)
        } else throw Exception("Bad request")
    }

    @Transactional
    override fun removeTraining(userId: Long, trainingId: Long): List<Training> {
        val training = trainingService.getById(trainingId)
        val user = this.getById(userId)
        if (user.removeTraining(training)) {
            this.save(user)
            return trainingService.getUserTrainings(userId)
        } else throw Exception("Bad request")
    }

    override fun refreshUserAchievements(userId: Long): List<Achievement> {
        val user = this.getById(userId)
        val achievements = achievementService.getAll()
                .filter { achievement -> !user.achievements.contains(achievement) }
        user.achievements.addAll(achievementService.getNewUserAchievements(user, achievements))
        return this.save(user).achievements
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