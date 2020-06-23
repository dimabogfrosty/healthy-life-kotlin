package by.jcoldsun.healthy_life.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReportControllerTests : BaseControllerTests() {

    @Test
    fun `should Return Report For User`() {
        mvc.perform(get("$BASE_URL$GET_USERS/$USER_ID$GET_REPORT")).andExpect(status().isOk)
    }

}