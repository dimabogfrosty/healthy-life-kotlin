package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.impl.UserServiceImpl
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserServiceTests : BaseServiceTests() {
    @InjectMocks
    lateinit var userService: UserServiceImpl
    lateinit var user: User

    @Before
    fun setUp() {
        user = createUser()
    }

    @Test
    fun `when GetByUsername Then Return User`() {
        `when`(userRepository.findByUsername(user.username)).thenReturn(user)

        val actualUser = userService.getByUsername(user.username)

        assertThat(actualUser.username, `is`(user.username))
        verify(userRepository, times(1)).findByUsername(user.username)
    }

    @Test
    fun `when GetByEmail Then Return User`() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(user)

        val actualUser = userService.getByEmail(user.email)

        assertThat(actualUser.email, `is`(user.email))
        verify(userRepository, times(1)).findByEmail(user.email)
    }

}