package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.util.stream.Collectors.toList
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = ["roles", "achievements", "trainings", "records"])
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
        @SequenceGenerator(name = "user_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
        var id: Long? = null,
        private var username: String = "",
        private var password: String = "",
        @Column(name = "first_name") var firstName: String = "",
        @Column(name = "last_name") var lastName: String = "",
        @Column(name = "birth_date") var birthDate: LocalDate? = null,
        @Column(name = "img_src") var imageSource: String? = null,
        var email: String = "",
        var gender: String = "",
        var weight: Double = 0.0,
        var height: Double = 0.0,
        @ManyToMany(targetEntity = Role::class)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")]
        )
        var roles: MutableList<Role> = arrayListOf(),
        @ManyToMany(targetEntity = Achievement::class)
        @JoinTable(
                name = "users_achievements",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "achievement_id")]
        )
        var achievements: MutableList<Achievement> = arrayListOf(),
        @ManyToMany(targetEntity = Training::class)
        @JoinTable(
                name = "users_trainings",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "training_id")]
        )
        var trainings: MutableList<Training> = arrayListOf(),
        @OneToMany(mappedBy = "user", targetEntity = Record::class)
        var records: List<Record> = arrayListOf()) : UserDetails {

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles.stream()
            .map { role -> SimpleGrantedAuthority(role.name) }.collect(toList())

    @JsonIgnore
    override fun isEnabled() = true

    override fun getUsername() = username

    fun setUsername(username: String) {
        this.username = username
    }

    @JsonIgnore
    override fun getPassword() = password

    @JsonProperty
    fun setPassword(password: String) {
        this.password = password
    }

    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    @JsonIgnore
    override fun isAccountNonExpired() = true

    @JsonIgnore
    override fun isAccountNonLocked() = true

    fun addRole(role: Role) {
        this.roles.add(role)
        role.users.add(this)
    }

    fun addAchievement(achievement: Achievement) {
        this.achievements.add(achievement)
        achievement.users.add(this)
    }

    fun addTraining(training: Training) {
        this.trainings.add(training)
        training.users.add(this)
    }
}
