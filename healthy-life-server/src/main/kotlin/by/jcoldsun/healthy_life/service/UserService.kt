package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.User
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : BaseService<User>, UserDetailsService {
    fun getByUsername(username: String): User?
    fun getByEmail(email: String): User?
    fun getAllUserWithPagination(page: Int, size: Int): List<User>
}