package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.exception.entity.RoleNotFoundException
import by.jcoldsun.healthy_life.repository.RoleRepository
import by.jcoldsun.healthy_life.service.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun getByName(name: String) = roleRepository.findByName(name)

    override fun getAll(): MutableList<Role> = roleRepository.findAll()

    override fun getById(id: Long): Role = roleRepository.findById(id)
            .orElseThrow { RoleNotFoundException("Role with id = $id does not exist") }

    @Transactional
    override fun save(entity: Role) = roleRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = roleRepository.delete(getById(id))
}