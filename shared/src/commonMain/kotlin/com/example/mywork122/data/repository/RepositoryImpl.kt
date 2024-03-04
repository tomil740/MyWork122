package com.example.mywork122.data.repository

import com.example.mywork122.core.flowWarpers.mapers.toCommonFlow
import com.example.mywork122.core.flowWarpers.util.CommonFlow
import com.example.mywork122.data.localDbFactory.mapers.toDeclareModelObj
import com.example.mywork122.data.localDbFactory.mapers.toWeekSumModelObj
import com.example.mywork122.database.WorkDatabase
import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.model.StatisticsWeeksData
import com.example.mywork122.domain.model.Targets
import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.domain.repository.Repository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus


class RepositoryImpl(
    db: WorkDatabase
):Repository{

    private val dao = db.workArchiveQueries


    override suspend fun insertDeclare(declare: Declare): Boolean {

        var theId:Int? = null
        if(declare.declareId!=0)
            theId = declare.declareId

        try {
            dao.insertDeclareEntity(declareId =theId?.toLong(), date = declare.date.toString(),
                workTime = declare.workTime.toDouble(), daySumId = declare.date.toString() )
        } catch (e: Exception) {
            return false
        }

        return true
    }


    override fun getDeclariesByDate(date: LocalDate): CommonFlow<List<Declare>> {
       return dao.getDeclaresByDate(date = date.toString()).asFlow()
                .mapToList().map { list -> list.map { it.toDeclareModelObj() } }
                .toCommonFlow()

    }

    override suspend fun getLastDeclare(): Declare {
        return  dao.getLastDeclare().executeAsOne().toDeclareModelObj()
    }

    override suspend fun getLastWeekSum(): WeekSum {
        return dao.getLastWeekSum().executeAsOne().toWeekSumModelObj()
    }

    override suspend fun insertWeekSum(weekSum: WeekSum, weekTarget: Float, dayTarget: Float) {
        dao.insertWeekSumEntity(
            weekWork = weekSum.weekWork.toDouble(), weekId = weekSum.weekId, startDate = weekSum.startDate.toString()
        )
    }

    override suspend fun insertTargets(targetObj: Targets) {
        dao.insertTargetEntity(
                targetObj.weekTarget.toDouble(),
                targetObj.dayTarget.toDouble()
        )
    }

    override suspend fun insertStatisticsObj(statisticsObj: StatisticsWeeksData) {
        dao.insertWeekSumStatisticsEntity(
                totalWork = statisticsObj.totalWork, totalWeeks = statisticsObj.totalWeeks.toLong(),
                avgWeek = statisticsObj.avgWeek.toDouble(), avgDay = statisticsObj.avgDay.toDouble()
        )
    }

    override suspend fun deleteDeclareById(theId: Int){
        dao.deleteDeclareById(theId.toLong())
    }

    override fun getTargetsObj(): Targets {

        val a =  dao.getTargetsObj().executeAsOne()

        return Targets(a.weekTarget.toFloat(),a.dayTarget.toFloat())

    }

    override fun getStatisticsObj(): StatisticsWeeksData {
        val a =   dao.getStatisticsObj().executeAsOne()


        return StatisticsWeeksData(
            totalWork = a.totalWork, totalWeeks = a.totalWeeks.toInt(),
            avgWeek = a.avgWeek.toFloat()
            , avgDay = a.avgDay.toFloat())


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllWeekSumFlow(): CommonFlow<List<WeekSum>> {
        return dao.getAllWeekSumFlow().asFlow().mapToList().
        flatMapConcat { weekSumList ->

            val result: MutableList<WeekSum> = mutableListOf()

            weekSumList?.forEach {
                val a = getWeekSumWithAll(it.weekId)
                result.add(a)
            }

            flow<List<WeekSum>> {
                emit(result)
            }
        }.toCommonFlow()
    }

    suspend fun getWeekSumWithAll(weekId:String):WeekSum{

        val targets = dao.getTargetsObj().executeAsOne()

        val weekSumObj = dao.getWeekSumByDate(weekId).executeAsOne()

        val daySumsObjLst = dao.getDaySumByWeekId(theId = weekSumObj.weekId).executeAsList()

        val outComeDays = mutableListOf<DaySum>()

        for (day in daySumsObjLst){
            val declares = dao.getDeclaresByDate(day.daySumId).executeAsList()
            outComeDays.add(
                DaySum(
                    date = LocalDate.parse(day.daySumId),
                    totalWork = day.totalWork.toFloat(),
                    dayTarget = targets.dayTarget.toFloat(),
                    declareLst = declares.map { it.toDeclareModelObj() }
                )
            )
        }

        return WeekSum(
           weekWork = weekSumObj.weekWork.toFloat(), startDate = LocalDate.parse(weekSumObj.startDate),
            endDate =  LocalDate.parse(weekSumObj.startDate).plus(DatePeriod(days = 6)),weekId = weekSumObj.weekId,
            daySums = outComeDays
        )
    }



    override suspend fun getAllWeekSum(): CommonFlow<List<WeekSum>> {
        return dao.getAllWeekSums().asFlow().mapToList().map { it.map {
           it.toWeekSumModelObj() }
        }.toCommonFlow()
    }

    override suspend fun getDeclareById ( theId : Int):Declare{
        return dao.getDeclareById(theId = theId.toLong()).executeAsOne().toDeclareModelObj()
    }


}