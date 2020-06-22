package by.jcoldsun.healthy_life.exception.web

import by.jcoldsun.healthy_life.exception.entity.*
import by.jcoldsun.healthy_life.exception.web.model.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [
        UserNotFoundException::class,
        RoleNotFoundException::class,
        RecordNotFoundException::class,
        AchievementNotFoundException::class,
        TrainingNotFoundException::class,
        ExerciseNotFoundException::class,
        DayOfTrainingNotFoundException::class])
    fun handleEntityNotFoundException(ex: Exception, request: WebRequest) = ResponseEntity(ExceptionResponse(LocalDateTime.now(), ex.message!!), HttpStatus.NOT_FOUND)
}