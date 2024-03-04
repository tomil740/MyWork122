package com.example.mywork122.presentation.addEditBuilder


import com.example.mywork122.presentation.entry.util.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class AddEditUiState (

    val date:String  = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString(),
    val errorMessage:String = "WorkTimeEligal",
    val day : String = LocalDate.parse(date).dayOfWeek.toString(),
    val workTime:String = "",
    val uiEvent: UiEvent? = null,
    val declareId : Int = 0
)

