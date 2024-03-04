package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.repository.Repository
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus


class ShouldUpdateWeekSum(private val repository: Repository) {

    suspend operator fun invoke (weekId : String):String{

        var lastDecWeekId = try {
            repository.getLastDeclare().date
        }catch (e:Exception){
            //in case there is nothing in the db , no need to update anything
            return weekId
        }

        if (lastDecWeekId.dayOfWeek.isoDayNumber != 7)
            lastDecWeekId= lastDecWeekId.minus(DatePeriod(days = lastDecWeekId.dayOfWeek.isoDayNumber))


        val lastDeclareWeekId = lastDecWeekId.toString()

        if(lastDeclareWeekId == weekId)
            return weekId

        val lastWeekSumId = try {
            repository.getLastWeekSum().weekId
        }catch (e:Exception){
            //if there is no weekSums object in the db , equals to date is not the same...
            return lastDeclareWeekId
        }

         if(lastDeclareWeekId == lastWeekSumId)
            return weekId

        else{
            return lastDeclareWeekId
        }


    }


}