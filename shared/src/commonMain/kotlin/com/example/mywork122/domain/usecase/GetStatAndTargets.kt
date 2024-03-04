package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.StatisticsWeeksData
import com.example.mywork122.domain.model.Targets
import com.example.mywork122.domain.model.TargetsAndStat
import com.example.mywork122.domain.repository.Repository


class GetStatAndTargets(private val repository: Repository) {

   suspend operator fun invoke(): TargetsAndStat {

        var a = Targets(8f, 46f)
        var b = StatisticsWeeksData(1.0, 1, 1.0f, 1f)


        try {
            a = repository.getTargetsObj()
        } catch (e: Exception) {
           // throw
            val a = Exception("the exception is : $e ||proberaly try to run from main thread , anyway could be there is no object in the db " +
                    "(if its the first initializon ) , just make shure this" +
                    "the reason to throw that exception is because you could end up not notice why the data is not updating")
        }
       try {
           b = repository.getStatisticsObj()

       }catch (e:Exception){
           //("exceptions",
           val a = ("exception on getStateAndTargets use case , cause this is the first week and you try" +
                   "to calculate the data from the null history data" +
                   "the actual exception should be null poninter and is :$e")

       }

            return TargetsAndStat(
                avgDay = b.avgDay,
                avgWeek = b.avgWeek,
                weekTarget = a.weekTarget,
                dayTarget = a.dayTarget
            )

    }
}

