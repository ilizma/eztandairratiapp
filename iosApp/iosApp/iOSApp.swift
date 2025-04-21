import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        MainViewControllerKt.initialise()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
