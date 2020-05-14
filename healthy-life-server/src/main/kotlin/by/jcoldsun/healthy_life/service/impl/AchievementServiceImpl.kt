package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Achievement
import by.jcoldsun.healthy_life.exception.entity.AchievementNotFoundException
import by.jcoldsun.healthy_life.repository.AchievementRepository
import by.jcoldsun.healthy_life.service.AchievementService
import org.springframework.stereotype.Service

@Service
class AchievementServiceImpl(private val achievementRepository: AchievementRepository) : AchievementService {
    override fun getAchievementByName(name: String) = achievementRepository.findByName(name)
            ?: throw AchievementNotFoundException("Achievement with name = $name does not exist")

    override fun getAll(): MutableList<Achievement> = achievementRepository.findAll()

    override fun getById(id: Long): Achievement = achievementRepository.findById(id)
            .orElseThrow { AchievementNotFoundException("Achievement with id = $id does not exist") }

    override fun save(entity: Achievement) = achievementRepository.saveAndFlush(entity)

    override fun delete(id: Long) = achievementRepository.delete(getById(id))
}