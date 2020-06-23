package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class UserController(private val userService: UserService,
                     private val roleService: RoleService,
                     private val achievementService: AchievementService,
                     private val recordService: RecordService,
                     private val trainingService: TrainingService) {

    @GetMapping("/admin/users")
    fun getAllUsers() = ResponseEntity(userService.getAll(), HttpStatus.OK)

    @GetMapping(name = "/admin/users", params = ["page", "size"])
    fun getAllUsersWithPagination(@RequestParam page: Int, @RequestParam size: Int) = ResponseEntity(userService.getAllUserWithPagination(page - 1, size), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}")
    fun getUserById(@PathVariable id: Long) = ResponseEntity(userService.getById(id), HttpStatus.OK)

    @GetMapping("/admin/users/{id:\\d+}/roles")
    fun getUserRoles(@PathVariable id: Long) = ResponseEntity(roleService.getRolesByUser(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/achievements")
    fun getUserAchievements(@PathVariable id: Long) = ResponseEntity(achievementService.getUserAchievements(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/records")
    fun getUserRecords(@PathVariable id: Long) = ResponseEntity(recordService.getUserRecords(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/trainings")
    fun getUserTrainings(@PathVariable id: Long) = ResponseEntity(trainingService.getUserTrainings(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/own/trainings")
    fun getUserOwnTrainings(@PathVariable id: Long) = ResponseEntity(trainingService.getUserOwnTraining(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/achievements/refresh")
    fun refreshUserAchievements(@PathVariable id: Long) = ResponseEntity(userService.refreshUserAchievements(id), HttpStatus.OK)

    @GetMapping("/users/{id:\\d+}/achievements/score")
    fun getAllAchievementsWithScore(@PathVariable id: Long) = ResponseEntity(userService.getUserAchievementsWithScore(id), HttpStatus.OK)

    @PostMapping("/users/registration")
    fun createUser(@RequestBody user: User) = ResponseEntity(userService.save(user), HttpStatus.CREATED)

    @PostMapping("/users/{userId:\\d+}/trainings")
    fun createTrainingByUser(@PathVariable userId: Long, @RequestBody training: Training): ResponseEntity<Training> {
        training.author = userService.getById(userId)
        return ResponseEntity(trainingService.save(training), HttpStatus.OK)
    }

    @PutMapping("/users")
    fun updateUser(@RequestBody user: User) = ResponseEntity(userService.save(user), HttpStatus.OK)

    @PutMapping("/admin/users/{userId:\\d+}/roles/{roleId:\\d+}")
    fun updateUserRole(@PathVariable userId: Long, @PathVariable roleId: Long) = ResponseEntity(userService.addRole(userId, roleId), HttpStatus.OK)

    @PutMapping("/admin/users/{userId:\\d+}/achievements/{achievementId:\\d+}")
    fun updateUserAchievement(@PathVariable userId: Long, @PathVariable achievementId: Long) = ResponseEntity(userService.addAchievement(userId, achievementId), HttpStatus.OK)

    @PutMapping("/users/{userId:\\d+}/trainings/{trainingId:\\d+}")
    fun updateUserTraining(@PathVariable userId: Long, @PathVariable trainingId: Long) = ResponseEntity(userService.addTraining(userId, trainingId), HttpStatus.OK)

    @DeleteMapping("/admin/users/{id:\\d+}")
    fun deleteUser(@PathVariable id: Long) = ResponseEntity(userService.delete(id), HttpStatus.OK)

    @DeleteMapping("/admin/users/{userId:\\d+}/roles/{roleId:\\d+}")
    fun deleteUserRole(@PathVariable userId: Long, @PathVariable roleId: Long) = ResponseEntity(userService.removeRole(userId, roleId), HttpStatus.OK)

    @DeleteMapping("/admin/users/{userId:\\d+}/achievements/{achievementId:\\d+}")
    fun deleteUserAchievement(@PathVariable userId: Long, @PathVariable achievementId: Long) = ResponseEntity(userService.removeAchievement(userId, achievementId), HttpStatus.OK)

    @DeleteMapping("/users/{userId:\\d+}/trainings/{trainingId:\\d+}")
    fun deleteUserTraining(@PathVariable userId: Long, @PathVariable trainingId: Long) = ResponseEntity(userService.removeTraining(userId, trainingId), HttpStatus.OK)
}