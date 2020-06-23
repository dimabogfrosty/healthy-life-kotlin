package by.jcoldsun.healthy_life.entity

import java.time.LocalDate
import java.time.LocalTime

open class Report(private val records: List<Record>) {
    fun getAverageTime(): LocalTime = LocalTime.ofNanoOfDay(getTotalTime().toNanoOfDay() / records.size)

    fun getTotalTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay()
            .reduce { x: Long, y: Long -> x + y }.get())

    fun getMaxTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay().max(Long::compareTo).get())

    fun getAverageDistance() = getTotalDistance() / records.size

    fun getTotalDistance() = records.stream().map(Record::distance)
            .reduce { x: Double, y: Double -> x + y }.get()

    fun getMaxDistance() = records.stream().map(Record::distance).max(Double::compareTo).get()

    fun getAverageSpeed() = getAverageDistance() / (getAverageTime().toSecondOfDay() / 3600.toDouble())

    fun getMaxSpeed() = records.stream()
            .map { record -> record.distance / (record.getRunningTime().toSecondOfDay() / 3600.toDouble()) }
            .max(Double::compareTo).get()

    private fun runningTimeInNanoOfDay() = records.stream()
            .map { record -> record.getRunningTime().toNanoOfDay() }
}

class DateReport(records: List<Record>,
                 val start: LocalDate,
                 val end: LocalDate) : Report(records)