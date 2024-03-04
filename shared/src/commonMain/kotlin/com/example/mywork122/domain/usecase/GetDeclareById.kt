package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.repository.Repository

class GetDeclareById(private val repository: Repository) {

    suspend operator fun invoke(theId : Int): Declare {
        return repository.getDeclareById(theId)

    }

}