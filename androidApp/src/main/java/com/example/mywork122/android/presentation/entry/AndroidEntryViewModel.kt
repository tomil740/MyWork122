package com.example.mywork122.android.presentation.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywork122.domain.usecase.EntryUseCase
import com.example.mywork122.presentation.entry.EntryEvent
import com.example.mywork122.presentation.entry.EntryViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidEntryViewModel @Inject constructor(
    private val entryUseCase: EntryUseCase,
) : ViewModel() {

    private val viewModel by lazy {
        EntryViewmodel(
            entryUseCase = entryUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state




    fun onEvent(event: EntryEvent) {
        //Log.i("this","${viewModel.state.value.weekSum.weekWork}")

        viewModel.onEntryEvent(event)
    }
}