package com.example.mywork122.presentation.addEditBuilder

sealed class Add_edit_events {
    object onSubmit: Add_edit_events()
    object onDelete: Add_edit_events()
    data class onDateChange(val date:String): Add_edit_events()
    data class onWorkTimeChange(val workTime:String): Add_edit_events()
    data class onInitializeItem(val id:Int):Add_edit_events()

}