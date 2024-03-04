package com.example.mywork122.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


data class Declare(
    var date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date ,

    var workTime:Float = 0.0f,

    var declareId :Int,
)
