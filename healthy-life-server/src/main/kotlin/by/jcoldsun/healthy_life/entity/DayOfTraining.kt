package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "training_days")
data class DayOfTraining(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_day_id_seq")
        @SequenceGenerator(name = "training_day_id_seq", sequenceName = "training_days_id_seq", allocationSize = 1)
        var id: Long? = null,
        @ManyToOne(targetEntity = Training::class)
        @JoinColumn(name = "training_id")
        @JsonBackReference
        var training: Training? = null,
        @Column(name = "day_number") var day: Int? = null,
        @ManyToMany(targetEntity = Exercise::class)
        @JoinTable(
                name = "training_days_exercises",
                joinColumns = [JoinColumn(name = "training_day_id")],
                inverseJoinColumns = [JoinColumn(name = "exercise_id")]
        )
        var exercises: List<Exercise> = arrayListOf())
