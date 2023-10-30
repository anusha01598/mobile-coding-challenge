package kreativemynds.mvvmarchdemo.ui.activity

import kreativemynds.mvvmarchdemo.domain.model.Podcast

data class PodcastListState(
    val isLoading: Boolean = false,
    val podcasts: List<Podcast> = emptyList(),
    val error : String = ""
)
