package by.jcoldsun.healthy_life.controller

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseControllerTests {
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