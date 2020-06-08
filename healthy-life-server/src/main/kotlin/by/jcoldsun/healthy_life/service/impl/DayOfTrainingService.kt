package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.DayOfTraining
import by.jcoldsun.healthy_life.exception.entity.DayOfTrainingNotFoundException
import by.jcoldsun.healthy_life.repository.DayOfTrainingRepository
import by.jcoldsun.healthy_life.service.DayOfTrainingService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DayOfTrainingService(private val dayOfTrainingRepository: DayOfTrainingRepository) : DayOfTrainingService {

    override fun getAll(): MutableList<DayOfTraining> = dayOfTrainingRepository.findAll()

    override fun getById(id: Long): DayOfTraining = dayOfTrainingRepository.findById(id)
            .orElseThrow { DayOfTrainingNotFoundException("day of training with id = $id does not exist") }

    @Transactional
    override fun save(entity: DayOfTraining) = dayOfTrainingRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = dayOfTrainingRepository.delete(getById(id))
}