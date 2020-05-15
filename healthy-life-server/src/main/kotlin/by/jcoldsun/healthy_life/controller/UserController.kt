package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() = ResponseEntity(userService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getUserById(@PathVariable id: Long) = ResponseEntity(userService.getById(id), HttpStatus.OK)

    @PostMapping
    fun createUser(@RequestBody user: User) = ResponseEntity(userService.save(user), HttpStatus.CREATED)

    @PutMapping
    fun updateUser(@RequestBody user: User) = ResponseEntity(userService.save(user), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteUser(@PathVariable id: Long) = ResponseEntity(userService.delete(id), HttpStatus.OK)

}