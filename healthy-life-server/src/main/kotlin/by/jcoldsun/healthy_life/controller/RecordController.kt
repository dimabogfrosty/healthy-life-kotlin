package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.service.RecordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/records")
class RecordController(private val recordService: RecordService) {

    @GetMapping
    fun getAllRecords() = ResponseEntity(recordService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getRecordById(@PathVariable id: Long) = ResponseEntity(recordService.getById(id), HttpStatus.OK)

    @PostMapping
    fun createRecord(@RequestBody record: Record) = ResponseEntity(recordService.save(record), HttpStatus.CREATED)

    @PutMapping
    fun updateRecord(@RequestBody record: Record) = ResponseEntity(recordService.save(record), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteRecord(@PathVariable id: Long) = ResponseEntity(recordService.delete(id), HttpStatus.OK)
}