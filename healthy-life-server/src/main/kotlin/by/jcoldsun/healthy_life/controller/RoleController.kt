package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Role
import by.jcoldsun.healthy_life.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/admin/roles")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getAllRoles() = ResponseEntity(roleService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getUserById(@PathVariable id: Long) = ResponseEntity(roleService.getById(id), HttpStatus.OK)

    @PostMapping
    fun createRole(@RequestBody role: Role) = ResponseEntity(roleService.save(role), HttpStatus.CREATED)

    @PutMapping
    fun updateRole(@RequestBody role: Role) = ResponseEntity(roleService.save(role), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteRole(@PathVariable id: Long) = ResponseEntity(roleService.delete(id), HttpStatus.OK)
}