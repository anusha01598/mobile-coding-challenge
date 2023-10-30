package kreativemynds.mvvmarchdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kreativemynds.mvvmarchdemo.ui.activity.PodcastDetailScreen
import kreativemynds.mvvmarchdemo.ui.activity.PodcastListScreen
import kreativemynds.mvvmarchdemo.ui.activity.SharedViewModel

/**
 * Navigation composable graph to handle navigation between screen1 and screen2
 */
@Composable
fun NavigationGraph() {

    val sharedViewModel: SharedViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.PodcastListScreen.route
    ) {
        //Displaying the best podcasts
        composable(route = Screen.PodcastListScreen.route) {
            PodcastListScreen(navController = navController, viewModel = sharedViewModel)
        }
        //Details of the Podcast
        composable(
            route = Screen.PodcastDetailScreen.route,
        ) {
            PodcastDetailScreen(navController = navController, viewModel = sharedViewModel)
        }
    }
}