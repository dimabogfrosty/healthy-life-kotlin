package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Training
import by.jcoldsun.healthy_life.service.TrainingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/trainings")
class TrainingController(private val trainingService: TrainingService) {

    @GetMapping
    fun getAllTrainings() = ResponseEntity(trainingService.getAll(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun getTrainingById(@PathVariable id: Long) = ResponseEntity(trainingService.getById(id), HttpStatus.OK)

    @GetMapping("/{trainingId:\\d+}/days/{day:\\d+}/exercises")
    fun getTrainingDayExercises(@PathVariable trainingId: Long, @PathVariable day: Int)
            = ResponseEntity(trainingService.getTrainingDayExercises(trainingId, day), HttpStatus.OK)

    @PostMapping
    fun crateTraining(@RequestBody training: Training)
            = ResponseEntity(trainingService.save(training), HttpStatus.CREATED)

    @PutMapping
    fun updateTraining(@RequestBody training: Training) = ResponseEntity(trainingService.save(training), HttpStatus.OK)

    @DeleteMapping("/{id:\\d+}")
    fun deleteTraining(@PathVariable id: Long) = ResponseEntity(trainingService.delete(id), HttpStatus.OK)

}