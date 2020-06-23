package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class AchievementController(private val achievementService: AchievementService) {

    @GetMapping("/achievements")
    fun getAllAchievements() = ResponseEntity(achievementService.getAll(), HttpStatus.OK)

    @GetMapping("/achievements/{id:\\d+}")
    fun getAchievementById(@PathVariable id: Long) = ResponseEntity(achievementService.getById(id), HttpStatus.OK)

    @PostMapping("/admin/achievements")
    fun createAchievement(@RequestBody achievement: Achievement)
            = ResponseEntity(achievementService.save(achievement), HttpStatus.CREATED)

    @PutMapping("/admin/achievements")
    fun updateAchievement(@RequestBody achievement: Achievement)
            = ResponseEntity(achievementService.save(achievement), HttpStatus.OK)

    @DeleteMapping("/admin/achievements/{id:\\d+}")
    fun deleteAchievement(@PathVariable id: Long) = ResponseEntity(achievementService.delete(id), HttpStatus.OK)
}