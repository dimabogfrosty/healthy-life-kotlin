package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "records")
@JsonIgnoreProperties(value = ["user"])
data class Record(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_id_seq")
        @SequenceGenerator(name = "record_id_seq", sequenceName = "records_id_seq", allocationSize = 1)
        var id: Long? = null,
        @Column(name = "run_date") var runDate: LocalDate = LocalDate.now(),
        @Column(name = "start_time") var startTime: LocalTime = LocalTime.now(),
        @Column(name = "end_time") var endTime: LocalTime = LocalTime.now(),
        var distance: Double = 0.0,
        @ManyToOne(targetEntity = User::class) @JoinColumn(name = "user_id") var user: User? = null) {
        fun getRunningTime() = LocalTime.ofNanoOfDay(endTime.toNanoOfDay() - startTime.toNanoOfDay())
}