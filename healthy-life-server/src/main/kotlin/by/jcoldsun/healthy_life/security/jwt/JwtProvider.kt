package by.jcoldsun.healthy_life.security.jwt

import by.jcoldsun.healthy_life.exception.token.InvalidJwtAuthenticationException
import by.jcoldsun.healthy_life.security.model.InvalidTokenService
import by.jcoldsun.healthy_life.service.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider(private val userService: UserService, private val invalidTokenService: InvalidTokenService) {
    companion object {
        const val TOKEN_REQUEST_HEADER_PREFIX = "Bearer "
        const val AUTHORIZATION_HEADER = "Authorization"
        const val EXPIRATION_TIME_IN_SECONDS: Long = 86_400_000
    }

    @Value("\${jwt.secret.key}")
    lateinit var secretKey: String

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(userId: String?): String {
        val now = Instant.now()
        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(userId))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(EXPIRATION_TIME_IN_SECONDS)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val userDetails = userService.getById(getUserId(token).toLong())
        return UsernamePasswordAuthenticationToken(userDetails, userDetails?.password, userDetails?.authorities)
    }

    fun getUserId(token: String?): String = getClaimsFromToken(token).subject

    fun getClaimsFromToken(token: String?): Claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body

    fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader(AUTHORIZATION_HEADER)
        return if (token != null && token.startsWith(TOKEN_REQUEST_HEADER_PREFIX)) {
            token.replace(TOKEN_REQUEST_HEADER_PREFIX, "")
        }
        else null
    }

    fun isValidateToken(token: String?): Boolean {
        try {
            return !isTokenExpired(token) && !invalidTokenService.isExists(token)
        } catch (ex: Exception) {
            when (ex) {
                is JwtException,
                is IllegalArgumentException -> throw InvalidJwtAuthenticationException("Expired or invalid JWT token")
                else -> throw ex
            }
        }
    }

    fun getExpiration(token: String?): Date = getClaimsFromToken(token).expiration

    fun isTokenExpired(token: String?) = getExpiration(token).before(Date.from(Instant.now()))
}