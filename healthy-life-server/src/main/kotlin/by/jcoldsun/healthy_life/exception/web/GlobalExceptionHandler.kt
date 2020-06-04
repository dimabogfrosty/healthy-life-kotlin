package by.jcoldsun.healthy_life.exception.web

import by.jcoldsun.healthy_life.exception.entity.*
import by.jcoldsun.healthy_life.exception.token.InvalidJwtAuthenticationException
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
        RecordNotFoundException::class, AchievementNotFoundException::class])
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest) =
            ResponseEntity(ex.message?.let { response(LocalDateTime.now(), it) }, HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = [InvalidJwtAuthenticationException::class])
    fun handleInvalidJwtAuthenticationException(ex: EntityNotFoundException, request: WebRequest) =
            ResponseEntity(ex.message?.let { response(LocalDateTime.now(), it) }, HttpStatus.UNAUTHORIZED)

    private fun response(timestamp: Any, message: Any): Map<String, Any> {
        val body = LinkedHashMap<String, Any>()
        body["timestamp"] = timestamp
        body["message"] = message
        return body
    }
}