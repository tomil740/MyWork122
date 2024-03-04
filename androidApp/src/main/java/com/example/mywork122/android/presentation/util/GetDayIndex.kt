package com.example.mywork122.android.presentation.util

import java.time.LocalDate

fun getDayIndex():Int{
    val  a = LocalDate.now().dayOfWeek.value

    if(a == 7){
        return 0
    }

    return a

}