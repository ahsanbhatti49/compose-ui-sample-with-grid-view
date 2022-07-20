package compose.gridview.sample.utils

import androidx.navigation.NavHostController
import compose.gridview.sample.utils.Destinations.Filters
import compose.gridview.sample.utils.Destinations.GameDetails
import compose.gridview.sample.utils.Destinations.Search

object Destinations {
    const val Home = "home"
    const val GameDetails = "gameDetails"
    const val Search = "search"
    const val Filters = "filters"

    object GameDetailsArgs {
        const val GameId = "gameId"
    }
}

class Actions(navHostController: NavHostController) {
    val openSearch = {
        navHostController.navigate(Search)
    }

    val openFilter = {
        navHostController.navigate(Filters)
    }

    val openGameDetails: (Int) -> Unit = { gameId ->
        navHostController.navigate("$GameDetails/$gameId")
    }
}