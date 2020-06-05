package by.jcoldsun.healthy_life.service

import by.jcoldsun.healthy_life.entity.DateReport
import by.jcoldsun.healthy_life.entity.Report
import by.jcoldsun.healthy_life.entity.User

interface ReportService {
    fun createReport(user: User): Report?
    fun createReportByWeeks(user: User): List<DateReport>
    fun createReportByMonths(user: User): List<DateReport>
}