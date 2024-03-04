//
//  EntryScreen.swift
//  iosApp
//
//  Created by Tomi EEDF on 22/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

private let appMoudle = AppModule()


struct EntryScreen: View {
    private var entryUseCase: EntryUseCase
    @ObservedObject var viewModel: EntryIosViewModel
    
    init(entryUseCase: EntryUseCase) {
        self.entryUseCase = entryUseCase
        self.viewModel = EntryIosViewModel(entryUseCases: entryUseCase)
    }
    
    
    
    var body: some View {
        
        ZStack {
            
            VStack(alignment: .leading) {
                
                WeekSumCover(weekSum : viewModel.state.weekSum,
                             theTarget: String(viewModel.state.targetsAndStat.weekTarget), theUnit: "Hour",
                             showDaySum : viewModel.showDaySum,
                             onClickWeekSum : {viewModel.onClickWeekSum()},
                             onClickDaySum: {tt in viewModel.onDaySum(lst: tt)}
                )
                
                NavigationLink("add Edit"){
                    AddEditScreen(addEditUseCase: appMoudle.addEditUseCase)
                    
                }
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .top)
            
            VStack{
                
                if(viewModel.showDeclares){
                    if(viewModel.theDeclareList != nil){
                        
                        ForEach(viewModel.theDeclareList!,id: \.declareId) { dayS in
                            
                            NavigationLink(dayS.date.dayOfWeek.name){
                                AddEditScreen(addEditUseCase: appMoudle.addEditUseCase,theId: Int(dayS.declareId))
                                
                            }
                            
                            
                            Spacer(minLength: 5)
                            
                            NavigationLink(dayS.date.dayOfWeek.name){
                                AddEditScreen(addEditUseCase: appMoudle.addEditUseCase,theId: Int(dayS.declareId))
                                
                            }
                            
                            
                            Spacer(minLength: 5)
                        }
                    }
                }
            }
        }.onAppear{
            viewModel.startObserving()
        } .onDisappear {
            viewModel.dispose()
        }
    }
}
        
            
        
         
         
    



