package compose.gridview.sample.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import compose.gridview.sample.R
import compose.gridview.sample.api.model.Session
import compose.gridview.sample.api.network.ScreenState
import compose.gridview.sample.utils.theme.Purple700
import compose.gridview.sample.utils.theme.Teal200
import compose.gridview.sample.vm.MainViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    openSearch: () -> Unit,
    mainViewModel: MainViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(topBar = {
        HomeAppBar(
            title = stringResource(id = R.string.home_app_bar_title),
            openSearch = openSearch
        )
    },
        scaffoldState = scaffoldState,
        content = { discoveryList(mainViewModel) }
    )
}

@ExperimentalFoundationApi
@Composable
fun discoveryList(mainViewModel: MainViewModel) {
    val result = mainViewModel.sessionList
    val viewState = mainViewModel.uiState.observeAsState()
    when (viewState.value) {
        is ScreenState.Loading -> CircularProgressBar()
        is ScreenState.Error -> ErrorItem(mainViewModel.errorMsg)
        is ScreenState.Success -> setupLazyGrid(sessions = result)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun setupLazyGrid(sessions: List<Session>) {
    sessions.let { session ->
        LazyVerticalGrid(state = rememberLazyListState(), cells = GridCells.Fixed(2), content = {
            items(session.size) { index ->
                GridItem(sessionItem = session[index])
            }
        })
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GridItem(sessionItem: Session) {
    Card(
        elevation = 20.dp,
        backgroundColor = Black,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(250.dp)
            .fillMaxWidth()
            .clickable(enabled = true, onClick = { })
    ) {
        ConstraintLayout {
            val (image, title, rating) = createRefs()
            Image(
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(
                    data = sessionItem.current_track.artwork_url,
                    builder = {
                        placeholder(R.drawable.ic_baseline_error_24)
                        crossfade(true)
                    }
                ),
                contentDescription = stringResource(id = androidx.appcompat.R.string.abc_action_bar_home_description),
            )
            Text(
                text = sessionItem.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = Purple700,
                maxLines = 2,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(rating) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(
                    text = sessionItem.genres.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ),
                    color = Teal200,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }
        }
    }
}

