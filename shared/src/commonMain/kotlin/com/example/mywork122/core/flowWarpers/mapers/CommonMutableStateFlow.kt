package com.plcoding.translator_kmm.core.domain.core

import com.example.mywork122.core.flowWarpers.util.CommonMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)


