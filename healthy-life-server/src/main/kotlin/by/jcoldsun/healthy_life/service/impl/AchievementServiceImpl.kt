package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.AchievementNotFoundException
import by.jcoldsun.healthy_life.repository.AchievementRepository
import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AchievementServiceImpl(private val achievementRepository: AchievementRepository) : AchievementService {
    override fun getAchievementByName(name: String) = achievementRepository.findByName(name)
            ?: throw AchievementNotFoundException("Achievement with name = $name does not exist")

    override fun getUserAchievements(userId: Long) = achievementRepository.findAchievementsByUsersId(userId)

    override fun getNewUserAchievements(user: User, possibleAchievements: List<Achievement>): List<Achievement> {
        return possibleAchievements.filter { achievement -> isNewAchievement(user, achievement) }
    }

    override fun getAll(): MutableList<Achievement> = achievementRepository.findAll()

    override fun getById(id: Long): Achievement = achievementRepository.findById(id)
            .orElseThrow { AchievementNotFoundException("Achievement with id = $id does not exist") }

    @Transactional
    override fun save(entity: Achievement) = achievementRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = achievementRepository.delete(getById(id))

    private fun isNewAchievement(user: User, achievement: Achievement) = when (achievement.name) {
        //TODO("finish later")
        "1K meters at once" -> is1KMetersAtOnce(user.records)
        "Total 10K meters" -> true
        "Total 15K meters" -> true
        else -> false
    }

    private fun is1KMetersAtOnce(userRecodes: List<Record>): Boolean {
        return userRecodes.any { record -> record.distance > 1.0 }
    }
}