package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "trainings")
@JsonIgnoreProperties(value = ["users", "daysOfTrainings"])
data class Training(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_id_seq")
        @SequenceGenerator(name = "training_id_seq", sequenceName = "trainings_id_seq", allocationSize = 1)
        var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        @Column(name = "img_src") var imageSource: String? = null,
        @ManyToMany(mappedBy = "trainings", targetEntity = User::class) var users: MutableList<User> = arrayListOf(),
        @OneToMany(mappedBy = "training", targetEntity = DayOfTraining::class)
        var daysOfTrainings: MutableList<DayOfTraining> = arrayListOf()) {
        fun getTrainingDays() = daysOfTrainings.size
        fun getFollowersCount() = users.size
}

