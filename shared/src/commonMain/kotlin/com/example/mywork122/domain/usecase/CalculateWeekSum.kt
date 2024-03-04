package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.model.WeekSum
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class CalculateWeekSum {

    operator fun invoke(daySums1 : List<DaySum>, weekId:String): WeekSum {

        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        var workSum = 0.0f
        var practicWork:Float = 0.0f
        var theoryWork:Float =0.0f
        var totalBaseDays:Int =0
        var totalHomeDays:Int = 0
        var startDate: LocalDate = currentDate
        var endDate: LocalDate = currentDate
        val daySums : List<DaySum> = daySums1


        for (i in daySums1){

            workSum+=i.totalWork


            if (i.date.dayOfWeek.isoDayNumber == 7)
                startDate = i.date

            else if(i.date.dayOfWeek.isoDayNumber == 6)
                endDate = i.date

        }


        return WeekSum(weekWork = workSum, startDate, endDate,weekId, daySums)

    }

}
