package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.DayOfTraining

interface DayOfTrainingService : BaseService<DayOfTraining> {
    fun getDayOfTrainingByTrainingIdAndDay(trainingId: Long, day: Int): DayOfTraining
}