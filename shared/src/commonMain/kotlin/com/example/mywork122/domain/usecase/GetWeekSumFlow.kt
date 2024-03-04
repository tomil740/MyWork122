package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetWeekSumFlow(private val repository: Repository) {

    suspend operator fun invoke (): Flow<List<WeekSum>> {
        return repository.getAllWeekSumFlow()
    }

}