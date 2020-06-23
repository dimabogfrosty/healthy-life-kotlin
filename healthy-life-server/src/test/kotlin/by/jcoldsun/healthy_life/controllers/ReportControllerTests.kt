package by.jcoldsun.healthy_life.controllers

import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ReportControllerTests : BaseControllerTests() {

    @Test
    fun `should Return Report For User`() {
        mvc.perform(get("$BASE_URL$GET_USER/$USER_ID$GET_REPORT")).andExpect(status().isOk)
    }

}