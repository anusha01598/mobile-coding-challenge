package kreativemynds.mvvmarchdemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room DB Entity to store and track state of "favourite"
 */
@Entity
data class Favourite(
    @PrimaryKey val podcastId: String,
    val isFavourited: Boolean = false
)
