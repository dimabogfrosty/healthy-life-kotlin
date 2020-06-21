package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface  TrainingRepository : JpaRepository<Training, Long> {
    fun findByName(name: String): Training?
    fun findTrainingsByUsersId(usersId: User): List<Training>
    fun findTrainingsByAuthorId(authorId: Long): List<Training>
}