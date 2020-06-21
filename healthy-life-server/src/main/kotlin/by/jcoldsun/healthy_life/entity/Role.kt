package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "roles")
@JsonIgnoreProperties(value = ["users"])
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
        @SequenceGenerator(name = "role_id_seq", sequenceName = "roles_id_seq", allocationSize = 1)
        var id: Long? = null,
        var name: String? = "USER",
        @ManyToMany(mappedBy = "roles", targetEntity = User::class) var users: MutableList<User> = arrayListOf())