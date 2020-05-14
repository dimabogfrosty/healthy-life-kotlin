package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/achievements")
class AchievementController(private val achievementService: AchievementService) {

    @GetMapping
    fun getAllAchievements() = achievementService.getAll()

    @GetMapping("/{id:\\d+}")
    fun getAchievementById(@PathVariable id: Long) = achievementService.getById(id)

}