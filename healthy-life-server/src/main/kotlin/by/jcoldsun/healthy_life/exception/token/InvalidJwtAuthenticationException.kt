package by.jcoldsun.healthy_life.exception.token

import org.springframework.security.core.AuthenticationException

class InvalidJwtAuthenticationException(msg: String?) : AuthenticationException(msg)