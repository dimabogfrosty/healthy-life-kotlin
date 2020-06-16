package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.exception.entity.RecordNotFoundException
import by.jcoldsun.healthy_life.repository.RecordRepository
import by.jcoldsun.healthy_life.service.RecordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RecordServiceImpl(private val recordRepository: RecordRepository) : RecordService {
    override fun getUserRecords(userId: Long) = recordRepository.findRecordsByUsersId(userId)

    override fun getAll(): MutableList<Record> = recordRepository.findAll()

    override fun getById(id: Long): Record = recordRepository.findById(id)
            .orElseThrow { RecordNotFoundException("Record with id = $id does not found") }

    @Transactional
    override fun save(entity: Record) = recordRepository.saveAndFlush(entity)

    @Transactional
    override fun delete(id: Long) = recordRepository.delete(getById(id))
}