package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.repository.UserRepository
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate


open class BaseServiceTests {
    @Mock
    lateinit var userRepository: UserRepository
    @Mock
    lateinit var roleService: RoleService
    @Mock
    lateinit var achievementService: AchievementService
    @Mock
    lateinit var trainingService: TrainingService

    companion object {
        fun createUser() = User(1L, "jcoldsun", "jcoldsun",
                "Dima", "Bogoslovskiy", LocalDate.now(),
                "none", "dimabog@gmail.com",
                "Male", 73.2, 155.0)
    }
}