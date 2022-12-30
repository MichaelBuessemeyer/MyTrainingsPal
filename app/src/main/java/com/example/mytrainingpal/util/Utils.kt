package com.example.mytrainingpal.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun todayDate(): Date {
    val todayDate = LocalDateTime.now()
    return GregorianCalendar(
        todayDate.year,
        todayDate.monthValue,
        todayDate.dayOfMonth
    ).time
}

fun localDateToDate(localDate: LocalDateTime): Date {
    return GregorianCalendar(
        localDate.year,
        localDate.monthValue,
        localDate.dayOfMonth
    ).time
}

fun dateToLocalDate(date: Date): LocalDate {
    val calendar = GregorianCalendar()
    calendar.time = date
    return LocalDate.of(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}