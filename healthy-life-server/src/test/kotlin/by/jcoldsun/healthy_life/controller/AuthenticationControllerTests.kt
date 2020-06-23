package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.security.jwt.JwtProvider
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AuthenticationControllerTests : BaseControllerTests() {
    @Autowired
    lateinit var jwtProvider: JwtProvider

    @Test
    fun `should Not Allow Access To Unauthenticated User`() {
        mvc.perform(get(BASE_URL + GET_USERS)).andExpect(status().isForbidden)
    }

    @Test
    fun `should Generate Token For User`() {
        val token = jwtProvider.createToken(USER_ID)

        assertThat(token, notNullValue())

        mvc.perform(get(BASE_URL + GET_USERS).header(HEADER_AUTH, token)).andExpect(status().isOk)
    }
}