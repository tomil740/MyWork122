import SwiftUI
import shared

struct ContentView: View {
    
    private let appMoudle = AppModule()

	var body: some View {
        
        NavigationView{
                
                EntryScreen(entryUseCase: appMoudle.entryUseCase)
                .navigationTitle("Entry")
            
        }
        
        
	}
}
/*
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

*/

