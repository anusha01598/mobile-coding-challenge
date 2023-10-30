package kreativemynds.mvvmarchdemo.ui.navigation

/**
 * Screen  for Navigation controller
 */
sealed class Screen(val route: String) {
    object PodcastListScreen : Screen(route = "podcast_list_screen")
    object PodcastDetailScreen : Screen(route = "podcast_detail_screen")

}
