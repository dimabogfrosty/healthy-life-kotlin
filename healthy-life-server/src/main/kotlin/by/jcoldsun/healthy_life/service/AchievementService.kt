package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.model.AchievementScore

interface AchievementService : BaseService<Achievement> {
    fun getAchievementByName(name: String): Achievement?
    fun getUserAchievements(userId: Long): List<Achievement>
    fun getNewUserAchievements(user: User, possibleAchievements: List<Achievement>): List<Achievement>
    fun getAchievementsWithScore(user: User): List<AchievementScore>
}