package kreativemynds.mvvmarchdemo.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kreativemynds.mvvmarchdemo.R
import kreativemynds.mvvmarchdemo.domain.model.Podcast

//Compose Podcast Item where is showing thumbnail,title, publisher at podcast list at Screen1
//This is for a cell in list
@Composable
fun PodcastItem(
    podcast: Podcast,
    onItemClick: (Podcast) -> Unit,
    isFavourited: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(podcast) }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        PodCastThumbnailImage(
            data = podcast.thumbnail,
            contentDescription = "Thumbnail for ${podcast.title}"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            //Title in Screen1
            Text(
                text = podcast.title,
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            //publisher in Screen1
            Text(
                text = podcast.publisher,
                color = Color.Gray,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic
            )
            //Favourited label only if the podcast has been favourited, otherwise hide the label.
            if (isFavourited) {
                Text(
                    text = stringResource(R.string.favourited),
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun PodCastThumbnailImage(
    data: String,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {

        val painter = rememberAsyncImagePainter(
            //Coil image loader
            ImageRequest.Builder(LocalContext.current).data(data = data)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_image_place_holder)
                    error(R.drawable.ic_image_place_holder)
                }).build()
        )
        val painterState = painter.state

        Image(
            painter = painter, contentDescription = contentDescription, modifier = Modifier.clip(
                RoundedCornerShape(10.dp)
            )
        )

        if (painterState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}