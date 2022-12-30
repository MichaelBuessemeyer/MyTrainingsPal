package com.example.mytrainingpal.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun localDateToJavaDate(date: LocalDate): Date{
    return GregorianCalendar(
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
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

fun calendarFromDate(date: Date): GregorianCalendar
{
    val cal = GregorianCalendar()
        cal.time = date
    return cal
}

fun todayDate(): Date {
    val todayDate = LocalDate.now()
    return localDateToJavaDate(todayDate)
}