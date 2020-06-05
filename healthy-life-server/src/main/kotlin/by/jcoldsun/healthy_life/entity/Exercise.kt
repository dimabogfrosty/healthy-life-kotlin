package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "exercises")
@JsonIgnoreProperties(value = ["daysOfTraining"])
data class Exercise(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_id_seq")
        @SequenceGenerator(name = "exercise_id_seq", sequenceName = "exercises_id_seq", allocationSize = 1)
        var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        @Column(name = "image_src") var imageSource: String? = null,
        @Column(name = "number") var count: Int = 0, // number of times or distance
        var reiteration: Int = 0, // number of repetitions
        @ManyToMany(mappedBy = "exercises", targetEntity = DayOfTraining::class)
        var daysOfTraining: MutableList<DayOfTraining> = arrayListOf()
)
