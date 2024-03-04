//
//  UnitDisplay.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct UnitDisplay: View {
    var theVal : String
    var theUnit : String
    
   
    
    var body: some View {
        
        
            
        
        HStack(alignment: .firstTextBaseline,spacing: 12){
            Text(theVal)
                .font(.system(size: 44))
            
            
            Text(theUnit)
                .font(.system(size: 22))
            }
        }
    }


#Preview {
    UnitDisplay(theVal: "5", theUnit: "Hours")
}
