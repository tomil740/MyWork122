package com.example.mywork120.presentation.all


import com.example.mywork122.core.flowWarpers.util.CommonStateFlow
import com.example.mywork122.core.flowWarpers.util.toCommonStateFlow
import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.usecase.AllUseCase
import com.example.mywork122.presentation.addEditBuilder.AddEditUiState
import com.example.mywork122.presentation.all.AllEvents
import com.example.mywork122.presentation.all.AllUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class AllViewmodel(
    private val allUseCase: AllUseCase,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)


    val weekStart = allUseCase.getSunday.invoke(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)

    var targetAndStatJob:Job? = Job()

    private val daySum1 =
        MutableStateFlow(DaySum(weekStart, 0.0f, 0.0f, listOf()))
    private val daySum2 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 1)), 0.0f, 0.0f, listOf()))
    private val daySum3 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 2)), 0.0f, 0.0f, listOf()))
    private val daySum4 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 3)), 0.0f, 0.0f, listOf()))
    private val daySum5 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 4)), 0.0f, 0.0f, listOf()))
    private val daySum6 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 5)), 0.0f, 0.0f, listOf()))
    private val daySum7 =
        MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 6)), 0.0f, 0.0f, listOf()))

    private val weekSum =
        combine(daySum1, daySum2, daySum3, daySum4, daySum5, daySum6, daySum7) { array ->
            arrayOf(daySum1, daySum2, daySum3, daySum4, daySum5, daySum6, daySum7)
            allUseCase.calculateWeekSum.invoke(daySums1 = array.toList(),weekStart.toString())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(300),
            initialValue = allUseCase.calculateWeekSum.invoke(
                listOf(
                    daySum1.value,
                    daySum2.value,
                    daySum3.value,
                    daySum4.value,
                    daySum5.value,
                    daySum6.value,
                    daySum7.value
                ),weekStart.toString()
            )
        )

    private val _uiState:MutableStateFlow<AllUiState> = MutableStateFlow(AllUiState(currentWeekSum = weekSum.value,listOf()))
    val uiState:CommonStateFlow<AllUiState> = _uiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),
        AllUiState(currentWeekSum = weekSum.value,listOf())
    )
        .toCommonStateFlow()

    val a = updateTargetsAndStat()

    init {

        viewModelScope.launch {


            launch {
                weekSum.collect {weekSum->
                    _uiState.update {  it.copy(currentWeekSum = weekSum)}
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    allUseCase.getWeekSumFlow.invoke().collectLatest { theListOf->
                        withContext(Dispatchers.Main) {
                            _uiState.update {   it.copy(archiveWeeksSum = theListOf)}
                        }
                    }
                } catch (e: Exception) {//}
                }
            }


                launch {
                    allUseCase.getDaySumByDate(daySum1.value.date).collect {
                        daySum1.value = it

                    }
                }
                launch {
                    allUseCase.getDaySumByDate(daySum2.value.date).collect {
                        daySum2.value = it

                    }
                }


                launch {
                    allUseCase.getDaySumByDate(daySum3.value.date).collect {
                        daySum3.value = it
                    }
                }
                launch {
                    allUseCase.getDaySumByDate(daySum4.value.date).collect {
                        daySum4.value = it
                    }
                }
                launch {
                    allUseCase.getDaySumByDate(daySum5.value.date).collect {
                        daySum5.value = it
                    }
                }
                launch {
                    allUseCase.getDaySumByDate(daySum6.value.date).collect {
                        daySum6.value = it
                    }
                }
                launch {
                    allUseCase.getDaySumByDate(daySum7.value.date).collect {
                        daySum7.value = it
                    }
                }

            }
        }
    fun updateTargetsAndStat() {
        targetAndStatJob?.cancel()
        targetAndStatJob = viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val a = allUseCase.getStatAndTargets.invoke()
                withContext(Dispatchers.Main) {
                   _uiState.update { it.copy(targetsAndStat = a)}
                }
            }
        }
    }


    fun onAllEvent(events: AllEvents){

        when(events){
            is AllEvents.OnNavToBuild -> TODO()
            is AllEvents.OnNavToEntry -> TODO()
            is AllEvents.OnWeekSumClick -> TODO()
            is AllEvents.onDaySumClick -> TODO()

        }

    }



}