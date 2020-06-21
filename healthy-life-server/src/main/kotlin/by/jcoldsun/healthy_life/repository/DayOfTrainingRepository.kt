package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.DayOfTraining
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DayOfTrainingRepository : JpaRepository<DayOfTraining, Long> {
    fun findDayOfTrainingByTrainingIdAndDay(trainingId: Long, day: Int): DayOfTraining?
}