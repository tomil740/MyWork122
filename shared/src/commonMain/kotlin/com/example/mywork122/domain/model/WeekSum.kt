package com.example.mywork122.domain.model

import kotlinx.datetime.LocalDate


data class WeekSum(
    val weekWork:Float=0f,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val weekId : String,
    val daySums : List<DaySum>
)
