package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>): MutableStateFlow<T>