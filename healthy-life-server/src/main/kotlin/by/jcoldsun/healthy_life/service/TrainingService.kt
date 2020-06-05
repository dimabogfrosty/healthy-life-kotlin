package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Training


interface TrainingService : BaseService<Training> {
    fun getByName(name: String): Training
}