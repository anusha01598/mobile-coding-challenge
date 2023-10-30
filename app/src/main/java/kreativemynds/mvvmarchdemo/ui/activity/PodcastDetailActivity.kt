package kreativemynds.mvvmarchdemo.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kreativemynds.mvvmarchdemo.R
import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kreativemynds.mvvmarchdemo.ui.navigation.Screen
import org.jsoup.Jsoup

/*
This is to compose Screen2(Detail) as per design mockup
 */
@Composable
fun PodcastDetailScreen(
    navController: NavController,
    viewModel: SharedViewModel
) {
    val podcast = viewModel.podcastState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar {
            //navController?.popBackStack()
            navController.navigate(route = Screen.PodcastListScreen.route) {
                popUpTo(Screen.PodcastListScreen.route) {
                    inclusive = true
                }
            }
        }
        Spacer(modifier = Modifier.height(34.dp))
        TitleAndPublisher(podcast?.title ?: "No Title", podcast?.publisher ?: "No publisher")
        LoadThumbnailImage(
            data = podcast?.thumbnail ?: "",
            contentDescription = "Thumbnail for ${podcast?.title ?: ""}"
        )
        FavouriteButton(viewModel = viewModel)
        Description(text = Jsoup.parse(podcast?.description ?: "No description").text())

    }
}

/**
 * Compose Top bar that appears Back button with "Back"
 */
@Composable
fun TopBar(
    onNavigateBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "ArrowBack",
            modifier = Modifier.size(20.dp),
        )

        Text(text = "Back", fontSize = 18.sp, modifier = Modifier.clickable {
            onNavigateBack()
        })
    }
}

/**
 * Compose Title and publisher fields
 */
@Composable
fun TitleAndPublisher(title: String, publisher: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = publisher,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * large Thumbnail image
 */
@Composable
fun LoadThumbnailImage(
    data: String,
    contentDescription: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {

        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = data)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_image_place_holder)
                    error(R.drawable.ic_image_place_holder)
                }).build()
        )
        val painterState = painter.state

        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(200.dp)
                .height(200.dp)
        )
        //show progress indicator while loading, but finish loading too quickly
        // so we won't see it on  my end.
        if (painterState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}

/**
 * Favourite Button
 */
@Composable
fun FavouriteButton(viewModel: SharedViewModel) {

    val context = LocalContext.current
    var favouriteButtonLabel = context.getString(R.string.favourite)
    val podcast = viewModel.podcastState
    val favouriteStates = viewModel.favouriteStates
    val favourite = favouriteStates.find {
        it.podcastId == podcast?.id
    }
    favourite?.run {
        favouriteButtonLabel =
            if (this.isFavourited) context.getString(R.string.favourited) else context.getString(
                R.string.favourite
            )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            onClick = {
                if (favourite != null) {
                    viewModel.updateFavourite(favourite = favourite.copy(isFavourited = !favourite.isFavourited))
                } else {
                    // At initial, when you select favourite on a podcast, we don't have any in DB
                    //so create Favourite network manually with selected podcast's ID
                    viewModel.updateFavourite(
                        Favourite(
                            podcastId = podcast!!.id,
                            isFavourited = favouriteButtonLabel != context.getString(R.string.favourited)
                        )
                    )
                }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = favouriteButtonLabel)
        }
    }
}

@Composable
fun Description(text: String) {
    Text(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 20.dp),
        text = text,
        color = Color.Gray,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        lineHeight = 15.sp
    )
}