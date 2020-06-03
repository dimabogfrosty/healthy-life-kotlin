package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "exercises")
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
        var dayOfTraining: Int = 0, // number of the day for training
        @ManyToOne(targetEntity = Training::class)
        @JoinColumn(name = "training_id")
        @JsonBackReference
        var training: Training? = null) : Comparable<Exercise> {
    override fun compareTo(other: Exercise) = this.dayOfTraining.compareTo(other.dayOfTraining)
}
