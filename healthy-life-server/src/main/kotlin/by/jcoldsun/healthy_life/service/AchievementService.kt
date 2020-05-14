package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Achievement

interface AchievementService : BaseService<Achievement> {
    fun getAchievementByName(name: String): Achievement?
}