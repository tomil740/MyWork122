package com.example.mywork122.android.presentation.all

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywork120.presentation.all.AllViewmodel
import com.example.mywork122.domain.usecase.AllUseCase
import com.example.mywork122.domain.usecase.EntryUseCase
import com.example.mywork122.presentation.all.AllEvents
import com.example.mywork122.presentation.entry.EntryEvent
import com.example.mywork122.presentation.entry.EntryViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
    class AndroidAllViewModel @Inject constructor(
        private val allUseCase: AllUseCase,
    ) : ViewModel() {

        private val viewModel by lazy {
            AllViewmodel(
                allUseCase = allUseCase,
                coroutineScope = viewModelScope
            )
        }

        val state = viewModel.uiState




        fun onEvent(event: AllEvents) {
           // Log.i("this","${viewModel.state.value.weekSum.weekWork}")

            viewModel.onAllEvent(event)
        }
    }