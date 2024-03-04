//
//  EntryIosViewModel.swift
//  iosApp
//
//  Created by Tomi EEDF on 22/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension EntryScreen {
    @MainActor class EntryIosViewModel: ObservableObject {
        private var entryUseCases: EntryUseCase
        
        private let viewModel: EntryViewmodel
        
        @Published var showDeclares: Bool = false
        
        @Published var theDeclareList: [Declare]? = nil
        
        @Published var showDaySum: Bool = false
        
        @Published var state: EntryUiState = EntryUiState(
            weekSum: WeekSum(weekWork: 5.5, startDate: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), endDate: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), weekId: "String"
            , daySums: [DaySum(date: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), totalWork: 5.5, dayTarget: 5.5,
            declareLst: [Declare(date: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), workTime: 5.5, declareId: 5)]),
                        DaySum(date: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), totalWork: 5.5, dayTarget: 5.5,
                        declareLst: [Declare(date: Kotlinx_datetimeLocalDate.companion.parse(isoString: "2024-02-23"), workTime: 5.5, declareId: 5)])]),
            targetsAndStat: TargetsAndStat(avgDay: 5.2, avgWeek: 5.2, weekTarget: 5.2, dayTarget: 5.2),
            showSetTargets: false, uiEvent: nil)
                
        private var handle: DisposableHandle?
        
        
        
        init(entryUseCases: EntryUseCase) {
            self.entryUseCases = entryUseCases
            self.viewModel = EntryViewmodel(entryUseCase: entryUseCases, coroutineScope: nil)
        }
        
        
        func onDaySum(lst : [Declare]?){
            theDeclareList = lst
            
            if(lst == nil && (lst?.isEmpty) != nil){
                showDeclares = false
            }
            else{
                showDeclares = !showDeclares
            }
            
        }
        
        
        func onEvent(event: EntryEvent) {
                    self.viewModel.onEntryEvent(event: event)
        }
        
        func onClickWeekSum() {
            showDaySum = !showDaySum
        }
                
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
                   handle?.dispose()
        }
    }

    
    
}
