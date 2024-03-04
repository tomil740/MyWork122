//
//  SmallUnitDis.swift
//  iosApp
//
//  Created by Tomi EEDF on 26/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SmallUnitDis: View {
    var theVal : String
    var theUnit : String
    
    var body: some View {
        
        
        HStack(alignment: .firstTextBaseline,spacing: 12){
            Text(theVal)
                .font(.system(size: 22))
            
            
            Text(theUnit)
                .font(.system(size: 11))
        }
    }
}



#Preview {
    SmallUnitDis(theVal: "5", theUnit: "Hours")
}




        
            
        
     
