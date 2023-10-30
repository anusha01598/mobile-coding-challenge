package kreativemynds.mvvmarchdemo.ui.activity

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kreativemynds.mvvmarchdemo.R
import kreativemynds.mvvmarchdemo.ui.navigation.Screen

/*
This is to compose screen1(Podcast list) as per design mockup
Show a list of podcasts using the endpoint provided
 */
@Composable
fun PodcastListScreen(
    navController: NavController,
    viewModel: SharedViewModel
) {
    val context = LocalContext.current
    val podcastListState = viewModel.podcastListState
    val favouriteStates = viewModel.favouriteStates

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(R.string.podcasts),
            modifier = Modifier.padding(start = 24.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(podcastListState.podcasts) { podcast ->
                val favourite = favouriteStates.find {
                    it.podcastId == podcast.id
                }
                val isFavourited = favourite?.isFavourited ?: false

                PodcastItem(
                    podcast = podcast,
                    onItemClick = {
                        viewModel.selectPodcast(podcast)
                        navController.navigate(
                            route = Screen.PodcastDetailScreen.route
                        )
                    },
                    isFavourited = isFavourited
                )
                Divider(color = Color.Gray)
            }
        }
        if (podcastListState.error.isNotBlank()) {
            Toast.makeText(context, podcastListState.error, Toast.LENGTH_LONG).show()
        }
        if (podcastListState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}