package kreativemynds.mvvmarchdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kreativemynds.mvvmarchdemo.ui.navigation.NavigationGraph
import kreativemynds.mvvmarchdemo.ui.theme.MVVMArchDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMArchDemoTheme {
                NavigationGraph()
            }
        }
    }
}
