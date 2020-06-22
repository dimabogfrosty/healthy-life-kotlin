package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.entity.Report
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.AchievementNotFoundException
import by.jcoldsun.healthy_life.repository.AchievementRepository
import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AchievementServiceImpl(private val achievementRepository: AchievementRepository) : AchievementService {
    companion object {
        const val ONE_KILOMETER = 1.0
        const val TEN_KILOMETERS = 10.0
        const val FIFTEEN_KILOMETERS = 15.0
    }

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
        "1K meters at once" -> isDistanceAtOnce(user.records, ONE_KILOMETER)
        "Total 10K meters" -> isTotalDistance(Report(user.records), TEN_KILOMETERS)
        "Total 15K meters" -> isTotalDistance(Report(user.records), FIFTEEN_KILOMETERS)
        else -> false
    }

    private fun isDistanceAtOnce(userRecodes: List<Record>, distance: Double) = userRecodes.any { record -> record.distance > distance }

    private fun isTotalDistance(report: Report, distance: Double) = report.getTotalDistance() > distance
}