package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.service.RecordService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/records")
class RecordController(private val recordService: RecordService) {

    @GetMapping
    fun getAllRecords() = recordService.getAll()

    @GetMapping("/{id:\\d+}")
    fun getRecordById(@PathVariable id: Long) = recordService.getById(id)

}