package by.jcoldsun.healthy_life.security.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "invalid_tokens")
data class InvalidToken(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invalid_token_id_seq")
        @SequenceGenerator(name = "invalid_token_id_seq", sequenceName = "invalid_tokens_id_seq", allocationSize = 1)
        val id: Long? = null,
        var token: String? = null,
        var expiration: LocalDateTime? = null)

@Repository
interface InvalidTokenRepository : JpaRepository<InvalidToken, Long> {
    fun existsByToken(token: String?): Boolean
    fun findInvalidTokenByExpirationBefore(now: LocalDateTime?): MutableList<InvalidToken>
}

@Service
class InvalidTokenService(private val invalidTokenRepository: InvalidTokenRepository) {
    companion object {
        const val MILLISECONDS_FOR_SCHEDULED: Long = 86_400_000
    }

    fun save(token: String?, expiration: LocalDateTime?) {
        val invalidToken = InvalidToken()
        invalidToken.token = token
        invalidToken.expiration = expiration
        invalidTokenRepository.saveAndFlush(invalidToken)
    }

    fun isExists(token: String?) = invalidTokenRepository.existsByToken(token)

    @Async
    @Scheduled(fixedDelay = MILLISECONDS_FOR_SCHEDULED)
    fun cleanInvalidTokens() {
        val invalidTokens = invalidTokenRepository.findInvalidTokenByExpirationBefore(LocalDateTime.now())
        invalidTokenRepository.deleteAll(invalidTokens)
    }
}