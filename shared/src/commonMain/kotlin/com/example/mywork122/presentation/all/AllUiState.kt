package com.example.mywork122.presentation.all


import com.example.mywork122.domain.model.TargetsAndStat
import com.example.mywork122.domain.model.WeekSum

data class AllUiState(
    val currentWeekSum : WeekSum,
    val archiveWeeksSum : List<WeekSum>,
    val targetsAndStat: TargetsAndStat = TargetsAndStat(8f,8f,8f,8f)
)
