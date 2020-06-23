package by.jcoldsun.healthy_life.entity

import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.roundToLong

open class Report(private val records: List<Record>) {
    fun getAverageTime(): LocalTime = LocalTime.ofNanoOfDay(getTotalTime().toNanoOfDay() / records.size)

    fun getTotalTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay()
            .reduce { x: Long, y: Long -> x + y }.get())

    fun getMaxTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay().max(Long::compareTo).get())

    fun getAverageDistance() = (((getTotalDistance() / records.size) * 10).roundToLong() / 10).toDouble()

    fun getTotalDistance(): Double {
        val totalDistance = records.stream().map(Record::distance)
                .reduce { x: Double, y: Double -> x + y }.get()
        println("-------------------------------------------------------$totalDistance")
        return ((totalDistance * 100).roundToLong() / 100).toDouble()
    }

    fun getMaxDistance() = records.stream().map(Record::distance).max(Double::compareTo).get()

    fun getAverageSpeed(): Double {
        val averageSpeed = getAverageDistance() / (getAverageTime().toSecondOfDay() / 3600.toDouble())
        return ((averageSpeed * 100).roundToLong() / 100).toDouble()
    }

    fun getMaxSpeed(): Double {
        val maxSpeed = records.stream()
                .map { record -> record.distance / (record.getRunningTime().toSecondOfDay() / 3600.toDouble()) }
                .max(Double::compareTo).get()
        return ((maxSpeed * 100).roundToLong() / 100).toDouble()
    }

    private fun runningTimeInNanoOfDay() = records.stream()
            .map { record -> record.getRunningTime().toNanoOfDay() }
}

class DateReport(records: List<Record>,
                 val start: LocalDate,
                 val end: LocalDate) : Report(records)