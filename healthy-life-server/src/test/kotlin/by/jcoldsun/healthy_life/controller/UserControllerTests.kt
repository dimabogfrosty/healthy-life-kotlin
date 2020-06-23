package by.jcoldsun.healthy_life.controller

import org.junit.Ignore
import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerTests : BaseControllerTests() {

    @Ignore
    @Test
    fun `should Add Training For User`() {
        mvc.perform(put("$BASE_URL$GET_USERS/$USER_ID$PUT_TRAINING/$TRAINING_ID")).andExpect(status().isOk)
    }

    @Test
    fun `should Return User Trainings`() {
        mvc.perform(get("$BASE_URL$GET_USERS/$USER_ID$GET_TRAININGS")).andExpect(status().isOk)
    }

}