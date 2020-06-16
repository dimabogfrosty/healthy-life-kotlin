package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.exception.entity.TrainingNotFoundException
import by.jcoldsun.healthy_life.repository.TrainingRepository
import by.jcoldsun.healthy_life.service.TrainingService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TrainingServiceImpl(private val trainingRepository: TrainingRepository) : TrainingService {

    override fun getByName(name: String) = trainingRepository.findByName(name)
            ?: throw TrainingNotFoundException("Training with name = $name does not exist")

    override fun getUserTrainings(userId: Long) = trainingRepository.findTrainingsByUsersId(userId)

    override fun getAll(): MutableList<Training> = trainingRepository.findAll()

    override fun getById(id: Long): Training = trainingRepository.findById(id)
            .orElseThrow { TrainingNotFoundException("Training with id = $id does not exist") }

    @Transactional
    override fun save(entity: Training) = trainingRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = trainingRepository.delete(getById(id))
}