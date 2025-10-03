import SwiftUI
import EztandaIrratiapp

@main
struct iOSApp: App {
    
    init() {
        FirebaseInitKt.doInitFirebase()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
