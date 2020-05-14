package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.service.RoleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/roles")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getAllRoles() = roleService.getAll()

    @GetMapping("/{id:\\d+}")
    fun getUserById(@PathVariable id: Long) = roleService.getById(id)

}