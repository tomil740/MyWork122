package com.example.mywork122.presentation.entry

import com.example.mywork122.core.flowWarpers.util.CommonStateFlow
import com.example.mywork122.core.flowWarpers.util.toCommonStateFlow
import com.example.mywork122.domain.model.DaySum
import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.model.TargetsAndStat
import com.example.mywork122.domain.model.WeekSum
import com.example.mywork122.domain.usecase.EntryUseCase
import com.example.mywork122.presentation.entry.util.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


class EntryViewmodel (
    private val entryUseCase: EntryUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    var targetAndStatJob: Job? = Job()

    val weekStart = entryUseCase.getSunday.invoke(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    )
    val weekId = weekStart.toString()


    private val daySum1 =
        entryUseCase.getDaySumByDate(weekStart)//MutableStateFlow(DaySum(weekStart))
    private val daySum2 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 1)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 1))))
    private val daySum3 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 2)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 2))))
    private val daySum4 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 3)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 3))))
    private val daySum5 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 4)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 4))))
    private val daySum6 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 5)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 5))))
    private val daySum7 =
        entryUseCase.getDaySumByDate(weekStart.plus(DatePeriod(days = 6)))//MutableStateFlow(DaySum(weekStart.plus(DatePeriod(days = 6))))


    private val weekSum: StateFlow<WeekSum> =
        combine(daySum1, daySum2, daySum3, daySum4, daySum5, daySum6, daySum7) { array ->
            arrayOf(daySum1, daySum2, daySum3, daySum4, daySum5, daySum6, daySum7)
            (entryUseCase.calculateWeekSum.invoke(daySums1 = array.toList(), weekId))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(300),
            initialValue = WeekSum(
                5f, weekStart, weekStart, weekId,
                listOf<DaySum>(
                    DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
                    DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
                    DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
                    DaySum(weekStart, 5f, 5f, listOf())
                )
            )
        )

    private var _state = MutableStateFlow(EntryUiState(WeekSum(
        5f, weekStart, weekStart, weekId,
        listOf<DaySum>(
            DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
            DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
            DaySum(weekStart, 5f, 5f, listOf()), DaySum(weekStart, 5f, 5f, listOf()),
            DaySum(weekStart, 5f, 5f, listOf())
        )
    )))
    val state : CommonStateFlow<EntryUiState> =
        combine(weekSum, _state) {
            weekSum1, state1 ->
        EntryUiState(weekSum = weekSum1,state1.targetsAndStat,state1.showSetTargets,state1.uiEvent)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntryUiState(weekSum.value))
        .toCommonStateFlow()




    var shouldUpdateWeekSumJob: Job? = Job()


    fun shouldUpdateWeekSum() {

        shouldUpdateWeekSumJob?.cancel()
        shouldUpdateWeekSumJob = viewModelScope.launch {

            val shouldUpdate: String = entryUseCase.shouldUpdateWeekSum(weekId)


            if (shouldUpdate != weekId) {
                val sunday =
                    LocalDate.parse(shouldUpdate)//entryUseCase.getSunday(getDateFromWeekId(shouldUpdate.toString()))
                val t = entryUseCase.getDaySumByDate(sunday).stateIn(viewModelScope).value
                val b =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 1)))
                        .stateIn(viewModelScope).value
                val c =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 2)))
                        .stateIn(viewModelScope).value
                val d =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 3)))
                        .stateIn(viewModelScope).value
                val e =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 4)))
                        .stateIn(viewModelScope).value
                val f =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 5)))
                        .stateIn(viewModelScope).value
                val g =
                    entryUseCase.getDaySumByDate(sunday.plus(DatePeriod(days = 6)))
                        .stateIn(viewModelScope).value
                val oldWeekSum = entryUseCase.calculateWeekSum(
                    listOf(t, b, c, d, e, f, g),
                    sunday.toString()
                )
                //need to get here the week target to insert with the object
                entryUseCase.insertWeekSum(oldWeekSum, 0.0f, 0.0f)

                entryUseCase.updateStatisticsObj()
                updateTargetsAndStat()
            }
        }
    }


    init {
        shouldUpdateWeekSum()
    }


    fun updateTargetsAndStat() {
        /*
        targetAndStatJob?.cancel()
        targetAndStatJob = viewModelScope.launch {
           // CoroutineScope(Dispatchers.IO).launch {
                val a = entryUseCase.getStatAndTargets.invoke()
               // withContext(Dispatchers.Main) {
                    _state.update { it.copy(targetsAndStat = a) }

         */
        //}
        //   }
    }

    fun onEntryEvent(event: EntryEvent) {
        when (event) {

            EntryEvent.onAll -> TODO()

            EntryEvent.onAnalize -> TODO()

            EntryEvent.onNewDeclaer -> TODO()

            is EntryEvent.onDayTargetChange -> {

                val a = event.dayTar.filter { it.isDigit() || it.equals('.') }

                _state.update {
                    it.copy(targetsAndStat = _state.value.targetsAndStat.copy(dayTarget = a.toFloat()))
                }
            }

                is EntryEvent.onWeekTargetChange -> {
                    val a = event.weekTar.filter { it.isDigit() || it.equals('.') }

                    _state.update {
                        it.copy(targetsAndStat = _state.value.targetsAndStat.copy(weekTarget = a.toFloat()))
                    }
                }

                is EntryEvent.onSubMitTargets -> {
                    //missing th use case itself...
                    /*
                    viewModelScope.launch {


                         val a = entryUseCase.
                               dayTar = event.targetObj.dayTar,
                               weekTar = event.targetObj.weekTar)
                        if (a) {
                            sendUiEvent(UiEvent.ShowSnackbar("secsess"))
                            updateTargetsAndStat()
                        }

                        else {
                            sendUiEvent(UiEvent.ShowSnackbar("values is eligal"))
                        }
                    }



                    _state.value = _state.value.copy(showSetTargets = false)


 */

                }

                EntryEvent.onShowTargetBuilder -> {
                    _state.update { it.copy(showSetTargets = !it.showSetTargets)}
                }

            }
        }

        fun sendUiEvent(event: UiEvent) {

        }
    }





