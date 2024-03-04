package com.example.mywork122.android.presentation.addEditBuilder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywork122.domain.usecase.AddEditUsecase
import com.example.mywork122.presentation.addEditBuilder.Add_edit_Viewmodel
import com.example.mywork122.presentation.addEditBuilder.Add_edit_events
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddEditViewModel @Inject constructor(
    private val addEditUseCase: AddEditUsecase,
) : ViewModel() {

    private val viewModel by lazy {
        Add_edit_Viewmodel(
            addEditUsecase = addEditUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: Add_edit_events) {
        viewModel.onAddEditEvent(event)
    }
}