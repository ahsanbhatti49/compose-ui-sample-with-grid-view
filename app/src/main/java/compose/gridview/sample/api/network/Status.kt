package compose.gridview.sample.api.network


sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    object Error : ScreenState()
}