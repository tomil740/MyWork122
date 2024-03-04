package com.example.mywork122.data.localDbFactory.mapers

import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.model.Declare
import database.DaySumEntity
import kotlinx.datetime.LocalDate

fun DaySumEntity.toModelObj(dayTarget: Float = 0.0f,declareLst:List<Declare> = listOf()):DaySum{
        return DaySum(
            date = LocalDate.parse(this.daySumId),
            totalWork= this.totalWork.toFloat(),
            dayTarget = dayTarget,
            declareLst = declareLst,
        )

}