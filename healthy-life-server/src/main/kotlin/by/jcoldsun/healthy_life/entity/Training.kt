package by.jcoldsun.healthy_life.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "trainings")
data class Training(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_id_seq")
        @SequenceGenerator(name = "training_id_seq", sequenceName = "trainings_id_seq", allocationSize = 1)
        var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        @ManyToMany(mappedBy = "trainings", targetEntity = User::class) var users: MutableList<User> = arrayListOf(),
        @OneToMany(mappedBy = "training", targetEntity = Exercise::class) var exercises: MutableSet<Exercise> = TreeSet()
)
