package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.repository.Repository

class OnDeleteDeclareById(private val repository: Repository) {

    suspend operator fun invoke(theId:Int){
        repository.deleteDeclareById(theId)
    }

}