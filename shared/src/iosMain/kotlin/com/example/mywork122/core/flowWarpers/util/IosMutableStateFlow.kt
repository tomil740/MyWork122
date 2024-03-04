package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T> (
    initialValue: T
): CommonMutableStateFlow<T>(MutableStateFlow(initialValue))