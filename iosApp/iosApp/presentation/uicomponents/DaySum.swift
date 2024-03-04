//
//  DaySum.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

 
 struct DaySumItem: View {
     var dayStr : String
     var dayWork: String
     var dayTarg :String
 
 var body: some View {
 
 HStack{
     SmallUnitDis(theVal: dayStr, theUnit: " ")
     
     SmallUnitDis(theVal: dayWork + " /", theUnit: "")
 
     SmallUnitDis(theVal: dayTarg, theUnit: "Hours")
 
 
 
 }
 }
 }
 
 #Preview {
     DaySumItem(dayStr: "sunday",dayWork: "7",dayTarg: "8")
 }
 
 
  
