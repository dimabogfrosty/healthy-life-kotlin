package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface RecordRepository : JpaRepository<Record, Long> {
    fun findRecordsByUserIdOrderByRunDate(userId: Long): List<Record>
    fun findRecordsByUserIdAndRunDateBetweenOrderByRunDate(userId: Long, from: LocalDate, to: LocalDate): List<Record>
}