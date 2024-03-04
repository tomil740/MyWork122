package com.example.mywork122.domain.usecase

data class AllUseCase(
    val getWeekId: GetWeekId,
    val getSunday: GetSunday,
    val getStatAndTargets: GetStatAndTargets,
    val calculateWeekSum: CalculateWeekSum,
    val getWeekSumFlow: GetWeekSumFlow,
    val getDaySumByDate: GetDaySumByDate,
)
