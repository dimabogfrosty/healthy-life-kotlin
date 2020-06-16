package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.Record

interface RecordService : BaseService<Record> {
    fun getUserRecords(userId: Long): List<Record>
}