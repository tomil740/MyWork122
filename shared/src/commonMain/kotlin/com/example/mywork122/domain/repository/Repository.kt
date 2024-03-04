package com.example.mywork122.domain.repository

import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.model.StatisticsWeeksData
import com.example.mywork122.domain.model.Targets
import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.core.flowWarpers.util.CommonFlow
import kotlinx.datetime.LocalDate


interface Repository {
    suspend fun insertDeclare(declare: Declare):Boolean

    fun getDeclariesByDate(date : LocalDate) : CommonFlow<List<Declare>>

    suspend fun getLastDeclare(): Declare

    suspend fun deleteDeclareById(theId: Int)

    suspend fun getLastWeekSum(): WeekSum

    suspend fun insertWeekSum(weekSum: WeekSum, weekTarget:Float, dayTarget:Float)

    suspend fun insertTargets(targetObj: Targets)

    suspend fun insertStatisticsObj(statisticsObj: StatisticsWeeksData)

    fun getTargetsObj(): Targets

    fun getStatisticsObj(): StatisticsWeeksData

    suspend fun getAllWeekSumFlow():CommonFlow<List<WeekSum>>

    suspend fun getAllWeekSum():CommonFlow<List<WeekSum>>

    suspend fun getDeclareById ( theId : Int): Declare
}