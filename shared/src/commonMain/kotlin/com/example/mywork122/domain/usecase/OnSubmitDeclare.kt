package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.repository.Repository
import kotlinx.datetime.LocalDate


class OnSubmitDeclare(private val repository: Repository) {

    suspend operator fun invoke(date:String,workTime:String,theId:Int) {
    //this use case should get the data from the builder and insert it by calling repository function

        //Log.i("on","obUseCase  $theId")

        repository.insertDeclare(
            Declare(
                LocalDate.parse(date), workTime = workTime.toFloat(), declareId = theId
            )
        )


    }


}