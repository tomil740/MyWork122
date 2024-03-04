package com.example.mywork122.domain.model

import kotlinx.datetime.LocalDate

data class DaySum(
    val date: LocalDate,
    val totalWork:Float = 0.0f,
    val dayTarget : Float = 0.0f,
    val declareLst: List<Declare> = listOf()
)
