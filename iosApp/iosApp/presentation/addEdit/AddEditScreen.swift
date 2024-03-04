//
//  AddEditScreen.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import UniformTypeIdentifiers
import shared

struct AddEditScreen: View {
    private var addEditUseCase: AddEditUsecase
    @ObservedObject var viewModel: AddEditIosViewModel
    private var theId : Int = 0
    
    init(addEditUseCase: AddEditUsecase,theId: Int = 0) {
        self.addEditUseCase = addEditUseCase
        self.viewModel = AddEditIosViewModel(addEditUseCase: addEditUseCase)
        self.theId = theId
        viewModel.initializeLearningItem(theId1: theId)

    }
    
    
    
    var body: some View {
        
        AddEditBuilder(date: Binding(get: { viewModel.state.date }, set: { value in
            viewModel.onEvent(event: Add_edit_events.onDateChange(date : value))})
                       , day: viewModel.state.day,
            workTime: Binding(get: { viewModel.state.workTime }, set: { value in
            viewModel.onEvent(event: Add_edit_events.onWorkTimeChange(workTime: value))})
                       ,submit: {viewModel.onEvent(event: Add_edit_events.onSubmit())}
                       ,delete: {viewModel.onEvent(event: Add_edit_events.onDelete())},
                       error: viewModel.state.errorMessage
        ).onAppear{viewModel.startObserving()} .onDisappear {
            viewModel.dispose()
        }
                                     

    }
}

//#Preview {
    //AddEditScreen()
//}
