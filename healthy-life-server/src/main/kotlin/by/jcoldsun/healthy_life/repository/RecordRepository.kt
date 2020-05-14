package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository : JpaRepository<Record, Long> {

}