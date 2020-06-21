package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.model.UserPagination
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : BaseService<User>, UserDetailsService {
    fun getByUsername(username: String): User
    fun getByEmail(email: String): User
    fun getAllUserWithPagination(page: Int, size: Int): UserPagination
    fun addRole(userId: Long, roleId: Long): List<Role>
    fun addAchievement(userId: Long, achievementId: Long): List<Achievement>
    fun addTraining(userId: Long, trainingId: Long): List<Training>
}