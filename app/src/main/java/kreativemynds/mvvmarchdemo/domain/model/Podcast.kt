package kreativemynds.mvvmarchdemo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Podcast(
    val id: String,
    val thumbnail: String,
    val title: String,
    val publisher: String,
    val description: String
) : Parcelable
