package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Training
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface  TrainingRepository : JpaRepository<Training, Long> {
    fun findByName(name: String): Training?
    fun findTrainingsByUsersId(id: Long): List<Training>
    fun findTrainingsByAuthor(authorId: Long): List<Training>
}