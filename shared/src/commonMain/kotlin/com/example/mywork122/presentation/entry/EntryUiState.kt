package com.example.mywork122.presentation.entry

import com.example.mywork122.domain.model.TargetsAndStat
import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.presentation.entry.util.UiEvent
import kotlinx.datetime.LocalDate


data class EntryUiState(
    val weekSum : WeekSum = WeekSum(5f, LocalDate.parse("2024-10-11"),
        LocalDate.parse("2024-10-11"),"2024-10-11", listOf()),
    val targetsAndStat: TargetsAndStat = TargetsAndStat(9f,9f,9f,9f),
    val showSetTargets : Boolean = false,
    val uiEvent : UiEvent? = null
)
