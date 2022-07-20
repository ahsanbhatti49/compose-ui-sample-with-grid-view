package compose.gridview.sample.api.model

data class MockedModel(
    val data: Data
)

data class Data(
    val sessions: List<Session>
)

data class Session(
    val current_track: CurrentTrack,
    val genres: List<String>,
    val listener_count: Int,
    val name: String
)

data class CurrentTrack(
    val artwork_url: String,
    val title: String
)