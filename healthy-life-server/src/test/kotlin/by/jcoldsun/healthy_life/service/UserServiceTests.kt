package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.service.impl.UserServiceImpl
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.*

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

}