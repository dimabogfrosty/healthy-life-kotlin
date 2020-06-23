package by.jcoldsun.healthy_life.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

open class BaseControllerTests {
    companion object {
        const val BASE_URL = "/api/v1"
        const val GET_USERS = "/users"
        const val USER_ID = "1"
        const val TRAINING_ID = "1"
        const val GET_REPORT = "/reports"
        const val GET_ACHIEVEMENTS = "/achievements"
        const val GET_RECORDS = "/records"
        const val HEADER_AUTH = "Authentication"
        const val PUT_TRAINING = "/trainings"
        const val GET_TRAININGS = "/trainings"
    }

    @Autowired
    lateinit var mvc: MockMvc
}