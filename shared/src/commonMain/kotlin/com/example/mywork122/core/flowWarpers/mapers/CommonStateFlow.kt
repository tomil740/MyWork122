package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.toCommonStateFlow() = CommonStateFlow(this)