//
//  AddEditIosViewModel.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
extension AddEditScreen {

    @MainActor class AddEditIosViewModel: ObservableObject {
        private var addEditUsecase: AddEditUsecase
        
        private let viewModel: Add_edit_Viewmodel
        
        @Published var state: AddEditUiState = AddEditUiState(date: "2024-10-11", errorMessage: "Clear", day: "Clear", workTime: "5", uiEvent: nil, declareId: 0)
     
        private var handle: DisposableHandle?
        
        
        
        init(addEditUseCase: AddEditUsecase) {
            self.addEditUsecase = addEditUseCase
            self.viewModel = Add_edit_Viewmodel(addEditUsecase: addEditUseCase, coroutineScope: nil)
        }
        
        func onEvent(event: Add_edit_events) {
            self.viewModel.onAddEditEvent(event: event)
        }
       
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func initializeLearningItem(theId1:Int){
            viewModel.initializeLearningItem(theId: Int32(theId1))
        }

        
        func dispose() {
                   handle?.dispose()
        }
    }



    }
