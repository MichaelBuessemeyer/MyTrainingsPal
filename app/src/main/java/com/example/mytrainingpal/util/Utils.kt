package com.example.mytrainingpal.util

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
