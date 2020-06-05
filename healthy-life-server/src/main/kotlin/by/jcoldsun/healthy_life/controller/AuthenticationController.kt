package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.security.jwt.JwtService
import by.jcoldsun.healthy_life.security.model.AuthenticationRequest
import by.jcoldsun.healthy_life.security.model.AuthenticationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(private val jwtService: JwtService) {

    @PostMapping("/signin")
    fun signIn(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity(jwtService.signIn(authenticationRequest), HttpStatus.OK)
    }

    @PostMapping("/signout")
    fun signOut(request: HttpServletRequest): ResponseEntity<Nothing> {
        jwtService.signOut(request)
        return ResponseEntity(HttpStatus.OK)
    }

}