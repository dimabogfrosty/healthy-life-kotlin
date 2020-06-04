package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Exercise
import by.jcoldsun.healthy_life.exception.entity.ExerciseNotFoundException
import by.jcoldsun.healthy_life.repository.ExerciseRepository
import by.jcoldsun.healthy_life.service.ExerciseService
import org.springframework.stereotype.Service

@Service
class ExerciseServiceImpl(private val exerciseRepository: ExerciseRepository) : ExerciseService {

    override fun getByName(name: String) = exerciseRepository.findByName(name)
            ?: throw ExerciseNotFoundException("Exercise with name = $name does not exist")

    override fun getAll(): MutableList<Exercise> = exerciseRepository.findAll()

    override fun getById(id: Long): Exercise = exerciseRepository.findById(id)
            .orElseThrow { ExerciseNotFoundException("Exercise with id = $id does not exist") }

    override fun save(entity: Exercise) = exerciseRepository.saveAndFlush(entity)

    override fun delete(id: Long) = exerciseRepository.delete(getById(id))
}