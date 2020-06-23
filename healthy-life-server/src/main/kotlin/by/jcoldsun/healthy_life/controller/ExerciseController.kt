package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.entity.Exercise
import by.jcoldsun.healthy_life.service.ExerciseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ExerciseController(private val exerciseService: ExerciseService) {

    @GetMapping("/exercises")
    fun getAllExercises() = ResponseEntity(exerciseService.getAll(), HttpStatus.OK)

    @GetMapping("/exercises/{id:\\d+}")
    fun getExerciseById(@PathVariable id: Long) = ResponseEntity(exerciseService.getById(id), HttpStatus.OK)

    @PostMapping("/admin/exercises")
    fun createExercise(@RequestBody exercise: Exercise)
            = ResponseEntity(exerciseService.save(exercise), HttpStatus.CREATED)

    @PutMapping("/admin/exercises")
    fun updateExercise(@RequestBody exercise: Exercise) = ResponseEntity(exerciseService.save(exercise), HttpStatus.OK)

    @DeleteMapping("/admin/exercises/{id:\\d+}")
    fun deleteExercise(@PathVariable id: Long) = ResponseEntity(exerciseService.delete(id), HttpStatus.OK)
}