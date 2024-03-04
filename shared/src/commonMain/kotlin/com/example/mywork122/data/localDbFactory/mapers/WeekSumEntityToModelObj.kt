package com.example.mywork122.data.localDbFactory.mapers

import com.example.mywork122.domain.model.WeekSum
import database.WeekSumEntity
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate

fun WeekSumEntity.toWeekSumModelObj() : WeekSum {

    return WeekSum(
        this.weekWork.toFloat(),
        LocalDate.parse(this.startDate),
        LocalDate.parse(this.startDate.plus(DatePeriod(days = 6))),
        this.weekId,
        listOf()
    )
}
