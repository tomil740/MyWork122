package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.model.Targets
import com.example.mywork122.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.LocalDate

class GetDaySumByDate(private val repository: Repository) {

     operator fun invoke(date: LocalDate): Flow<DaySum> {

       val dayTarget = try {
            repository.getTargetsObj().dayTarget
        }catch (e:Exception){
            5f
        }

          return repository.getDeclariesByDate(date).map {


                 val date1: LocalDate = date
                 var totalWork: Float = 0.0f
                 val declareLst = it

             if (it.isNotEmpty()) {
                 it.forEach {
                     totalWork += it.workTime
                 }
             }
                  DaySum(date1, totalWork, dayTarget, declareLst)
          }



             }





    }


