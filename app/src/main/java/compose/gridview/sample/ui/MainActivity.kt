package compose.gridview.sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import compose.gridview.sample.utils.theme.ComposeGridViewSampleTheme
import compose.gridview.sample.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGridViewSampleTheme {
                HomeScreen(openSearch = { /*TODO*/ }, mainViewModel = mainViewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeAppBar(
        title = "Discovery",
        openSearch = {
            /*TODO*/
        }
    )
}