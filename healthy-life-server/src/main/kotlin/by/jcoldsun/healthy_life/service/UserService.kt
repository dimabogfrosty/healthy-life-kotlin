package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.User

interface UserService : BaseService<User> {
    fun getByUsername(username: String): User?
    fun getByEmail(email: String): User?
}