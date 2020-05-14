package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AchievementRepository : JpaRepository<Achievement, Long> {
    fun findByName(name: String): Achievement?
}