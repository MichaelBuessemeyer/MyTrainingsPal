package com.example.mytrainingpal.util

import java.time.LocalDate
import java.util.*

fun localDateToJavaDate(date: LocalDate): Date{
    return GregorianCalendar(
        date.year,
        date.monthValue,
        date.dayOfMonth
    ).time
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

data class TrainingStats(
    val durationInMinutes: Int,
    val totalWeightLifted: Int,
    val date: Date?
)