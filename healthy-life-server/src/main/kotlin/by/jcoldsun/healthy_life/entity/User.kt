package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.util.stream.Collectors.toList
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = ["password"])
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
        var roles: List<Role> = arrayListOf(),
        @ManyToMany(targetEntity = Achievement::class)
        @JoinTable(
                name = "users_achievements",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "achievement_id")]
        )
        var achievements: List<Achievement> = arrayListOf(),
        @OneToMany(mappedBy = "user", targetEntity = Record::class)
        var records: List<Record> = arrayListOf()) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles.stream()
            .map { role -> SimpleGrantedAuthority(role.name) }.collect(toList())

    override fun isEnabled() = true

    override fun getUsername() = username

    override fun getPassword() = password

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}