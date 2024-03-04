package com.example.mywork122.domain.usecase

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus

class GetSunday {

    operator fun invoke(date: LocalDate):LocalDate{
        var current = (date.dayOfWeek).isoDayNumber

        if (current == 7)
            return date

        return date.minus(DatePeriod(days = current))
    }

}