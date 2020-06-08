package by.jcoldsun.healthy_life.repository

import by.jcoldsun.healthy_life.entity.User
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTests {

    @Autowired
    lateinit var testEntityManager: TestEntityManager
    @Autowired
    lateinit var userRepository: UserRepository

    val user: User = User()

    @Before
    fun setUp() {
        user.username = "jcoldsun"
        user.password = "1111"
        user.email = "dima_diplom@gmail.com"
        testEntityManager.persist(user)
        testEntityManager.flush()
    }

    @Test
    fun `When findByUsername then return User`() {
        val actualUser = userRepository.findByUsername(user.username)
        assertThat(actualUser, Matchers.equalTo(user))
    }

    @Test
    fun `When findByEmail then return User`() {
        val actualUser = userRepository.findByEmail(user.email)
        assertThat(actualUser, Matchers.equalTo(user))
    }
}