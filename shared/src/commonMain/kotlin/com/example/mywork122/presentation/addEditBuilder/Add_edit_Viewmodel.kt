package com.example.mywork122.presentation.addEditBuilder


import com.example.mywork122.core.flowWarpers.util.toCommonStateFlow
import com.example.mywork122.domain.model.Declare
import com.example.mywork122.domain.usecase.AddEditUsecase
import com.example.mywork122.presentation.entry.util.UiEvent
import com.example.mywork122.presentation.util.MyWorkDestinationRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

import kotlin.Exception

class Add_edit_Viewmodel(
    private val addEditUsecase: AddEditUsecase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)


    private var _state =  MutableStateFlow(AddEditUiState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddEditUiState())
        .toCommonStateFlow()


    private var initializeJob: Job? = null



    //this function will check and intialize the flows state if there is existing Todo object here or set deffault values to the new object to be created
    fun initializeLearningItem(theId:Int) {
        initializeJob?.cancel()
        //check if we open an todo or new one if its we load it to our ui states
        initializeJob = viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                if (theId != 0) {

                    var theObj: Declare = Declare(declareId = 0)

                    try {
                        theObj = addEditUsecase.getDeclareById.invoke(theId = theId)
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            //Log.i("error", "error e:$e")
                            sendUiEvent(UiEvent.ShowSnackbar("error while try to initialize object , this is new declare"))
                        }
                    }
                    withContext(Dispatchers.Main) {
                        _state.value = _state.value.copy(
                            date = theObj.date.toString(),
                            workTime = theObj.workTime.toString(),
                            declareId = theObj.declareId
                        )

                    }
                    } else {
                        sendUiEvent(UiEvent.ShowSnackbar("This Is New Declare"))
                    }

            }
        }
    }




    fun onAddEditEvent(event: Add_edit_events) {
        when (event) {
            Add_edit_events.onDelete -> {

                viewModelScope.launch {
                    if (_state.value.declareId != (0)) {
                        withContext(Dispatchers.IO) {
                            addEditUsecase.onDeleteDeclareById(_state.value.declareId)
                        }
                    } else {
                        sendUiEvent(UiEvent.ShowSnackbar("there is no item to delete..."))
                    }
                    //in order to prevent from the nav to heapned while we try to send an toast an that wiil
                    //cause an error
                sendUiEvent(UiEvent.Navigate(MyWorkDestinationRoutes.Entry.toString()))
                }

            }

            Add_edit_events.onSubmit -> {
                if (addEditUsecase.onlegalityCheck(state.value.date,state.value.workTime) != "Clear") {
                    sendUiEvent(UiEvent.ShowSnackbar("fix errors before try to send again"))

                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        addEditUsecase.onSubmitDeclare(
                            state.value.date,
                            state.value.workTime,
                            theId = state.value.declareId

                        )
                        sendUiEvent(UiEvent.ShowSnackbar("work"))
                        //to prevent crash we will send it in main scope and wait before
                        delay(200)
                        withContext(Dispatchers.Main) {
                            sendUiEvent(UiEvent.Navigate(MyWorkDestinationRoutes.Entry.toString()))
                        }

                    }
                }
            }

            is Add_edit_events.onDateChange -> {
                val day = try {
                    LocalDate.parse(event.date).dayOfWeek.toString()
                }catch (e:Exception){"Eligal Date"}
                val mes = addEditUsecase.onlegalityCheck(event.date,state.value.workTime)
                _state.value = _state.value.copy(date = event.date, day = day, errorMessage = mes)
            }

            is Add_edit_events.onWorkTimeChange -> {
                val mes = addEditUsecase.onlegalityCheck(state.value.date,event.workTime)
                _state.value = state.value.copy(workTime = event.workTime, errorMessage = mes)
            }

            is Add_edit_events.onInitializeItem -> initializeLearningItem(event.id)
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _state.update { state.value.copy(uiEvent = event )}
        }
    }



}