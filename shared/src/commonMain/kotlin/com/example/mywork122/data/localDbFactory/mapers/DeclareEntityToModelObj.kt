package com.example.mywork122.data.localDbFactory.mapers

import com.example.mywork122.domain.model.Declare
import database.DeclareEntity
import kotlinx.datetime.LocalDate

fun DeclareEntity.toDeclareModelObj():Declare{


        val declare = this

        return  Declare(
            date = LocalDate.parse(declare.date),
            workTime = declare.workTime.toFloat(),
            declareId = declare.declareId.toInt(),
        )


    }