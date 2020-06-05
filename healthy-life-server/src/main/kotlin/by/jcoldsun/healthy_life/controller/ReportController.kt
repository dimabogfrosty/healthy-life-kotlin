package by.jcoldsun.healthy_life.controller

import by.jcoldsun.healthy_life.service.ReportService
import by.jcoldsun.healthy_life.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users/{id:\\d+}/reports")
class ReportController(private val reportService: ReportService, private val userService: UserService) {

    @GetMapping
    fun createReport(@PathVariable id: Long) = ResponseEntity(reportService.createReport(userService.getById(id)!!), HttpStatus.OK)

    @GetMapping("/weeks")
    fun createReportByWeeks(@PathVariable id: Long) = ResponseEntity(reportService.createReportByWeeks(userService.getById(id)!!), HttpStatus.OK)

    @GetMapping("/months")
    fun createReportByMonths(@PathVariable id: Long) = ResponseEntity(reportService.createReportByMonths(userService.getById(id)!!), HttpStatus.OK)
}