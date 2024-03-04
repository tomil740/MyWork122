//
//  WeekSumCover.swift
//  iosApp
//
//  Created by Tomi EEDF on 22/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct WeekSumCover: View {
    var weekSum : WeekSum
    var theTarget: String
    var theUnit : String
    var showDaySum : Bool
    var onClickWeekSum : () -> Void
    var onClickDaySum : ([Declare]?) -> Void
    var body: some View {
        
        let theVal : String = String(weekSum.weekWork)
        let theWeek : String = weekSum.weekId
                
        
        ZStack(alignment: Alignment(horizontal: .center, vertical: .top)){
            
            VStack{
                Button(action:onClickWeekSum ){
                    Text("the week start in :" + theWeek)            .frame(maxHeight: 250, alignment: .top).padding(.vertical, 15)
                }
                
                Spacer(minLength: 5)
                
                HStack(){
                    UnitDisplay(theVal:theVal,theUnit:
                                    theUnit).frame(maxWidth: .infinity, alignment: .leading).padding(.horizontal,25)
                    
                    UnitDisplay(theVal: theTarget,theUnit: theUnit).frame(maxWidth:.infinity, alignment: .trailing).padding(.horizontal,25)
                    
                }
                
                if(showDaySum){
                    
                    VStack{
                    
                        ForEach(weekSum.daySums,id: \.date) { dayS in
                            
                            Button(action : {onClickDaySum(dayS.declareLst)}){
                                DaySumItem.init(dayStr : dayS.date.dayOfWeek.name,dayWork: String(dayS.totalWork), dayTarg: String(dayS.dayTarget))
                                // dayS.declareLst
                                
                                
                                Spacer(minLength: 5)
                            }
                        }
                    }
                }
                    
                
                
                
            }
            
        }.frame(maxWidth: .infinity,maxHeight:  120,alignment: .top).background(Color.green)
        
    }
}

