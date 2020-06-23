package by.jcoldsun.healthy_life.controller

import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AchievementControllerTests : BaseControllerTests() {

    @Test
    fun `should Return Achievements For User`() {
        mvc.perform(get("$BASE_URL$GET_USERS/$USER_ID$GET_ACHIEVEMENTS")).andExpect(status().isOk)
    }

}