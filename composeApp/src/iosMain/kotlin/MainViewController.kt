import androidx.compose.ui.window.ComposeUIViewController
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

fun MainViewController() = ComposeUIViewController {
    Firebase.initialize()
    App()
}