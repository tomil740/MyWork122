package com.example.mywork122.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetWeekId() {

    operator fun invoke(
        date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    ):
            Int {
        //will get thursday , so the monday to sunday week dosnt make any different for us here
        //and the week will be +1 in order to keep counting from 1 and not 0
        return (date.year + ((date.dayOfYear / 7) + 1) * 10000)

    }
}