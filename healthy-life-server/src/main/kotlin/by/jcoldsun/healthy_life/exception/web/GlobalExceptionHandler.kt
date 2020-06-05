package by.jcoldsun.healthy_life.exception.web

import by.jcoldsun.healthy_life.exception.entity.*
import by.jcoldsun.healthy_life.exception.web.model.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [UserNotFoundException::class, RoleNotFoundException::class,
        RecordNotFoundException::class, AchievementNotFoundException::class,
        TrainingNotFoundException::class, ExerciseNotFoundException::class,
        DayOfTrainingNotFoundException::class])
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest) =
            ResponseEntity(ex.message?.let { response(LocalDateTime.now(), it) }, HttpStatus.NOT_FOUND)

    private fun response(timestamp: Any, message: Any): ExceptionResponse = ExceptionResponse(timestamp, message)
}