package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.DayOfTraining
import by.jcoldsun.healthy_life.service.DayOfTrainingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/days")
class DayOfTrainingController(private val dayOfTrainingService: DayOfTrainingService) {

    @GetMapping
    fun getAllDaysOfTraining() = ResponseEntity(dayOfTrainingService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getDayOfTrainingById(@PathVariable id: Long) = ResponseEntity(dayOfTrainingService.getById(id), HttpStatus.OK)

    @PostMapping
    fun createDayOfTraining(@RequestBody dayOfTraining: DayOfTraining) =
            ResponseEntity(dayOfTrainingService.save(dayOfTraining), HttpStatus.CREATED)

    @PutMapping
    fun updateDayOfTraining(@RequestBody dayOfTraining: DayOfTraining) =
            ResponseEntity(dayOfTrainingService.save(dayOfTraining), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteDayOfTraining(@PathVariable id: Long) = dayOfTrainingService.delete(id)
}