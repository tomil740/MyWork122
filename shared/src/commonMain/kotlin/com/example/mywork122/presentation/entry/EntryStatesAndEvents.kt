package com.example.mywork122.presentation.entry


import com.example.mywork122.presentation.entry.util.TargetDataObj

data class EntryStatesAndEvents(
    val uiState:EntryUiState,
    val onSubmit : (TargetDataObj) ->Unit,
    val onDayTarg : (String) -> Unit,
    val onWeekTarg : (String) -> Unit,
    val onShowTargetBuilder : () -> Unit
)
