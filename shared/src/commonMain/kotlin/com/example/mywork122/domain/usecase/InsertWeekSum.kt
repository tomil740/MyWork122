package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.domain.repository.Repository


class InsertWeekSum(private val repository: Repository) {

    suspend operator fun invoke(weekSumModel : WeekSum, dayTarget:Float, weekTarget : Float){
        repository.insertWeekSum(weekSumModel,dayTarget,weekTarget)
    }

}