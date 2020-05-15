package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/achievements")
class AchievementController(private val achievementService: AchievementService) {

    @GetMapping
    fun getAllAchievements() = ResponseEntity(achievementService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getAchievementById(@PathVariable id: Long) = ResponseEntity(achievementService.getById(id), HttpStatus.OK)

    @PostMapping
    fun createAchievement(@RequestBody achievement: Achievement)
            = ResponseEntity(achievementService.save(achievement), HttpStatus.CREATED)

    @PutMapping
    fun updateAchievement(@RequestBody achievement: Achievement)
            = ResponseEntity(achievementService.save(achievement), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteAchievement(@PathVariable id: Long) = ResponseEntity(achievementService.delete(id), HttpStatus.OK)
}