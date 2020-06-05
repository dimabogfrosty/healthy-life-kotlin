package by.jcoldsun.healthy_life.service.impl

import by.jcoldsun.healthy_life.entity.DateReport
import by.jcoldsun.healthy_life.entity.Record
import by.jcoldsun.healthy_life.entity.Report
import by.jcoldsun.healthy_life.entity.User
import by.jcoldsun.healthy_life.repository.RecordRepository
import by.jcoldsun.healthy_life.service.ReportService
import org.springframework.stereotype.Service
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.*

@Service
class ReportServiceImpl(private val recordRepository: RecordRepository) : ReportService {

    override fun createReport(user: User): Report? {
        val records = recordRepository.findRecordsByUserIdOrderByRunDate(user.id!!)
        return if (records.isNotEmpty()) Report(records) else null
    }

    override fun createReportByWeeks(user: User): List<DateReport> {
        val records = recordRepository.findRecordsByUserIdOrderByRunDate(user.id!!)
        return if (records.isNotEmpty()) getDateReports(user.id!!, divideByWeeks(records)) else arrayListOf()
    }

    override fun createReportByMonths(user: User): List<DateReport> {
        val records = recordRepository.findRecordsByUserIdOrderByRunDate(user.id!!)
        return if (records.isNotEmpty()) getDateReports(user.id!!, divideByMonths(records)) else arrayListOf()
    }

    private fun getDateReports(userId: Long, dayIntervals: List<DayInterval>): List<DateReport> {
        val dateReports = arrayListOf<DateReport>()
        var records: List<Record>

        for (dayInterval in dayIntervals) {
            records = recordRepository
                    .findRecordsByUserIdAndRunDateBetweenOrderByRunDate(userId, dayInterval.start, dayInterval.end)
            if (records.isNotEmpty()) {
                dateReports.add(DateReport(records, dayInterval.start, dayInterval.end))
            }
        }

        return dateReports
    }

    private fun divideByWeeks(records: List<Record>): List<DayInterval> {
        val dayInterval = arrayListOf<DayInterval>()
        var start = records.first().runDate
        val end = records.last().runDate

        while (!end.isBefore(start.with(previousOrSame(MONDAY)))) {
            dayInterval.add(DayInterval(start.with(previousOrSame(MONDAY)), start.with(nextOrSame(SUNDAY))))
            start = start.plusWeeks(1)
        }

        return dayInterval
    }

    private fun divideByMonths(records: List<Record>): List<DayInterval> {
        val dayInterval = arrayListOf<DayInterval>()
        var start = records.first().runDate
        val end = records.last().runDate

        while (!end.isBefore(start.with(firstDayOfMonth()))) {
            dayInterval.add(DayInterval(start.with(firstDayOfMonth()), start.with(lastDayOfMonth())))
            start = start.plusMonths(1)
        }

        return dayInterval
    }

}

private class DayInterval(val start: LocalDate, val end: LocalDate)