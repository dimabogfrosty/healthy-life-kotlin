package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.entity.DateReport
import by.jcoldsun.healthy_life.entity.Report
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.exception.entity.AchievementNotFoundException
import by.jcoldsun.healthy_life.repository.AchievementRepository
import by.jcoldsun.healthy_life.service.AchievementService
import by.jcoldsun.healthy_life.service.ReportService
import by.jcoldsun.healthy_life.service.model.AchievementScore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AchievementServiceImpl(private val achievementRepository: AchievementRepository,
                             private val reportService: ReportService) : AchievementService {
    override fun getAchievementByName(name: String) = achievementRepository.findByName(name)
            ?: throw AchievementNotFoundException("Achievement with name = $name does not exist")

    override fun getUserAchievements(userId: Long) = achievementRepository.findAchievementsByUsersId(userId)

    override fun getNewUserAchievements(user: User, possibleAchievements: List<Achievement>): List<Achievement> {
        return possibleAchievements.filter { achievement -> getAchievementScore(user, achievement) > achievement.goal }
    }

    override fun getAchievementsWithScore(user: User): List<AchievementScore> {
        return getAll().map { achievement -> val score = getAchievementScore(user, achievement)
            AchievementScore(achievement, if (score < achievement.goal) score else achievement.goal) }
    }

    override fun getAll(): MutableList<Achievement> = achievementRepository.findAll()

    override fun getById(id: Long): Achievement = achievementRepository.findById(id)
            .orElseThrow { AchievementNotFoundException("Achievement with id = $id does not exist") }

    @Transactional
    override fun save(entity: Achievement) = achievementRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = achievementRepository.delete(getById(id))

    private fun getAchievementScore(user: User, achievement: Achievement) = when (achievement.name) {
        "1K meters at once" -> getDistanceAtOnceScore(Report(user.records))
        "Total 10K meters",
        "Total 15K meters" -> getTotalDistanceScore(Report(user.records))
        "Be faster!!!" -> getMaxAverageSpeedForAnyWeeks(reportService.createReportByWeeks(user))
        "Speed is our life" -> getMaxSpeed(Report(user.records))
        else -> 0.0
    }

    private fun getDistanceAtOnceScore(report: Report) = report.getMaxDistance()

    private fun getTotalDistanceScore(report: Report) = report.getTotalDistance()

    private fun getMaxAverageSpeedForAnyWeeks(weeksReport: List<DateReport>) = weeksReport.stream()
            .map(DateReport::getAverageSpeed)
            .max(Double::compareTo).get()

    private fun getMaxSpeed(report: Report) = report.getMaxSpeed()
}