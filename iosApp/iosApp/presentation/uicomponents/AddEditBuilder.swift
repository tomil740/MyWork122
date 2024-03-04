//
//  AddEditBuilder.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

let appModule = AppModule()


struct AddEditBuilder: View {
    @Binding var date: String
    var day : String
    @Binding var workTime : String
    var submit : () -> Void
    var delete : () -> Void
    var error : String

    /*
    init(date: String, day: String, workTime: String) {
        self.date = date
        self.day = day
        self.workTime = workTime
    }
     */
    
    var body: some View {
        
        VStack{
            
            HStack{
                
                Text(day).frame(
                    maxWidth: .infinity,
                    minHeight: 200,
                    alignment: .topLeading
                )
                
                TextField("Enter Date", text: $date).frame(
                    maxWidth: .infinity,
                    minHeight: 200,
                    alignment: .topLeading
                )
            }.frame(maxWidth: .infinity, alignment: .leading).padding(.horizontal,25)
            
            
            HStack{
                
                TextField("Enter time", text: $workTime).frame(
                    maxWidth: .infinity,
                    minHeight: 200,
                    alignment: .topLeading
                )
            }.frame(maxWidth: .infinity, alignment: .leading).padding(.horizontal,25)
            
            HStack{
                
                Button("submit", action : {
                    submit()
                })
                   
                        
                    
                    
                    //Text("Submit")
                    
                
                
                Button(action : delete){
                    
                    Text("Delete")
                    
                }
            }.frame(maxWidth: .infinity, alignment: .leading).padding(.horizontal,25)
            
            HStack{
                
              Text(error)
            }.frame(maxWidth: .infinity, alignment: .leading).padding(.horizontal,25)
            
            
            
        }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .top)
    }
    
}



// #Preview {
     //AddEditBuilder(date: Binding("hh"), day: "String", workTime: Binding<String>)
 //}
 
 
