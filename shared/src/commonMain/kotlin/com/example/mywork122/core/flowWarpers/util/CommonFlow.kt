package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.flow.Flow

expect class CommonFlow<T>(flow: Flow<T>): Flow<T>