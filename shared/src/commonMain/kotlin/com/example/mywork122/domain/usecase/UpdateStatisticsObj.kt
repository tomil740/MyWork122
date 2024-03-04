package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.StatisticsWeeksData
import com.example.mywork122.domain.repository.Repository


class UpdateStatisticsObj(private val repository: Repository) {

   suspend  operator fun invoke(){
/*
        val allWeekSum = repository.getAllWeekSum()

           var totalWork : Double = 0.0
           var practicWork : Double = 0.0
           var theoryWork : Double = 0.0
           var totalWeeks : Int = 0
           var avgWeek : Double = 0.0
           var avgDay : Double = 0.0

           for (i in allWeekSum){
               totalWeeks+=1
               totalWork+=i.weekWork
           }

       avgWeek = totalWork/(totalWeeks.toDouble())
       avgDay = totalWork/(totalWeeks*7.0)

       repository.insertStatisticsObj(
           StatisticsWeeksData(
              totalWork,practicWork,theoryWork,totalWeeks,avgWeek.toFloat(),avgDay.toFloat()
           )
       )


 */

    }

}