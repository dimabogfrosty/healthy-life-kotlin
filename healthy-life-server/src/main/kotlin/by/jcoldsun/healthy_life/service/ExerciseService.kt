package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Exercise

interface ExerciseService : BaseService<Exercise> {
    fun getByName(name: String): Exercise
}