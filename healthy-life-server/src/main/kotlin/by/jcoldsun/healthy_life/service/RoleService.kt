package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Role

interface RoleService : BaseService<Role> {
    fun getByName(name: String): Role?
}