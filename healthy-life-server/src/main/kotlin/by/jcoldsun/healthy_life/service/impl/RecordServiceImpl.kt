package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.exception.entity.RecordNotFoundException
import by.jcoldsun.healthy_life.repository.RecordRepository
import by.jcoldsun.healthy_life.service.RecordService
import by.jcoldsun.healthy_life.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RecordServiceImpl(private val recordRepository: RecordRepository,
                        private val userService: UserService) : RecordService {
    override fun getUserRecords(userId: Long) = recordRepository.findRecordsByUserId(userId)

    override fun getAll(): MutableList<Record> = recordRepository.findAll()

    override fun getById(id: Long): Record = recordRepository.findById(id)
            .orElseThrow { RecordNotFoundException("Record with id = $id does not found") }

    @Transactional
    override fun save(entity: Record): Record {
        val record = recordRepository.saveAndFlush(entity)
        userService.refreshUserAchievements(record.user!!.id!!)
        return record
    }

    @Transactional
    override fun delete(id: Long) = recordRepository.delete(getById(id))
}