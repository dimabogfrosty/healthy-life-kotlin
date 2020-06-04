package by.jcoldsun.healthy_life.security.model

data class AuthenticationRequest(
        val username: String? = null,
        val password: String? = null
)