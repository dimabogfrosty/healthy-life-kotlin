package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() = userService.getAll()

    @GetMapping("/{id:\\d+}")
    fun getUserById(@PathVariable id: Long) = userService.getById(id)

}