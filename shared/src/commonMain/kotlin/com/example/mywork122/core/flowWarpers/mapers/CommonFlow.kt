package com.example.mywork122.core.flowWarpers.mapers

import com.example.mywork122.core.flowWarpers.util.CommonFlow
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.toCommonFlow() = CommonFlow(this)