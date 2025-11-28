import SwiftUI
import ComposeApp
@main
struct iOSApp: App {
    
    init() {
        AppModulesKt.doInitKoin()
    }

    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
