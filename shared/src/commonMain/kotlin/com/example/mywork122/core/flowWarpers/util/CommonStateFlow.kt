package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.flow.StateFlow

expect class CommonStateFlow<T>(flow: StateFlow<T>): StateFlow<T>