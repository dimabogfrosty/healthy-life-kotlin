package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.DayOfTraining
import by.jcoldsun.healthy_life.exception.entity.DayOfTrainingNotFoundException
import by.jcoldsun.healthy_life.repository.DayOfTrainingRepository
import by.jcoldsun.healthy_life.service.DayOfTrainingService
import org.springframework.stereotype.Service

@Service
class DayOfTrainingService(private val dayOfTrainingRepository: DayOfTrainingRepository) : DayOfTrainingService {

    override fun getAll(): MutableList<DayOfTraining> = dayOfTrainingRepository.findAll()

    override fun getById(id: Long): DayOfTraining = dayOfTrainingRepository.findById(id)
            .orElseThrow { DayOfTrainingNotFoundException("day of training with id = $id does not exist") }

    override fun save(entity: DayOfTraining) = dayOfTrainingRepository.saveAndFlush(entity)

    override fun delete(id: Long) = dayOfTrainingRepository.delete(getById(id))
}