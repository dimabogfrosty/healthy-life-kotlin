package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long> {
    fun findByName(name: String): Exercise?
}