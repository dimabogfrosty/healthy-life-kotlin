package by.jcoldsun.healthy_life.security.jwt

import by.jcoldsun.healthy_life.security.model.AuthenticationRequest
import by.jcoldsun.healthy_life.security.model.AuthenticationResponse
import by.jcoldsun.healthy_life.security.model.InvalidTokenService
import by.jcoldsun.healthy_life.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class JwtService(private val authenticationManager: AuthenticationManager, private val jwtProvider: JwtProvider,
                 private val userService: UserService, private val invalidTokenService: InvalidTokenService) {
    fun signIn(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        val username = authenticationRequest.username!!
        val password = authenticationRequest.password!!
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        val token = jwtProvider.createToken(userService.getByUsername(username)?.id.toString())
        return AuthenticationResponse(userService.getByUsername(username)?.id!!, token)
    }

    fun signOut(request: HttpServletRequest) {
        val token = jwtProvider.resolveToken(request)
        if (token != null)
            invalidTokenService.save(token, convertFromDateToLocalDateTime(jwtProvider.getExpiration(token)))
    }

    private fun convertFromDateToLocalDateTime(date: Date) = date.toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime()
}