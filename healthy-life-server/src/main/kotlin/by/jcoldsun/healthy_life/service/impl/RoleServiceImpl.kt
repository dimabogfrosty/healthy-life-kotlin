package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.exception.entity.RoleNotFoundException
import by.jcoldsun.healthy_life.repository.RoleRepository
import by.jcoldsun.healthy_life.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun getByName(name: String) = roleRepository.findByName(name)

    override fun getAll(): MutableList<Role> = roleRepository.findAll()

    override fun getById(id: Long): Role = roleRepository.findById(id)
            .orElseThrow { RoleNotFoundException("Role with id = $id does not exist") }

    override fun save(entity: Role) = roleRepository.saveAndFlush(entity)

    override fun delete(id: Long) = roleRepository.delete(getById(id))
}