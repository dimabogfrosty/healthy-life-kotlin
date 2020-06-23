package by.jcoldsun.healthy_life.controller

import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class RecordControllerTests : BaseControllerTests() {

    @Test
    fun `should Return User Records`() {
        mvc.perform(get("$BASE_URL$GET_USERS/$USER_ID$GET_RECORDS")).andExpect(status().isOk)
    }

}