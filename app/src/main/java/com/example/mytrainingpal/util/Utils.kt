package com.example.mytrainingpal.util

import java.time.LocalDate
import java.util.*

fun localDateToJavaDate(date: LocalDate): Date {
    return GregorianCalendar(
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    ).time
}

fun localDateToOnlyDate(date: Date): Date {
    val cal = calendarFromDate(date)
    return GregorianCalendar(
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    ).time
}

fun calendarFromDate(date: Date): GregorianCalendar {
    val cal = GregorianCalendar()
    cal.time = date
    return cal
}

fun todayDate(): Date {
    val todayDate = LocalDate.now()
    return localDateToJavaDate(todayDate)
}