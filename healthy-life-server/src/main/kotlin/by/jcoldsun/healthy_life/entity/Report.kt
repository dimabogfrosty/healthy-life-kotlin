package by.jcoldsun.healthy_life.entity

import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

open class Report(private val records: List<Record>) {
    fun getAverageTime(): LocalTime = LocalTime.ofNanoOfDay(getTotalTime().toNanoOfDay() / records.size)
            .truncatedTo(ChronoUnit.SECONDS)

    fun getTotalTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay()
            .reduce { x: Long, y: Long -> x + y }.get())

    fun getMaxTime(): LocalTime = LocalTime.ofNanoOfDay(runningTimeInNanoOfDay().max(Long::compareTo).get())

    fun getAverageDistance() = round(getTotalDistance() / records.size, 1)

    fun getTotalDistance() = round(records.stream().map(Record::distance)
            .reduce { x: Double, y: Double -> x + y }.get(), 1)

    fun getMaxDistance() = round(records.stream().map(Record::distance).max(Double::compareTo).get(), 1)

    fun getAverageSpeed() = round(getAverageDistance() / (getAverageTime().toSecondOfDay() / 3600.toDouble()), 1)

    fun getMaxSpeed() = round(records.stream()
            .map { record -> record.distance / (record.getRunningTime().toSecondOfDay() / 3600.toDouble()) }
            .max(Double::compareTo).get(), 1)

    private fun runningTimeInNanoOfDay() = records.stream()
            .map { record -> record.getRunningTime().toNanoOfDay() }

    private fun round(num: Double, roundCount: Int) = String.format("%.$roundCount" + "f", num).toDouble()
}

class DateReport(records: List<Record>,
                 val start: LocalDate,
                 val end: LocalDate) : Report(records)