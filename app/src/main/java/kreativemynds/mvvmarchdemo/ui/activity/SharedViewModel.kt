package kreativemynds.mvvmarchdemo.ui.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kreativemynds.mvvmarchdemo.domain.common.BaseResponse
import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kreativemynds.mvvmarchdemo.domain.model.Podcast
import kreativemynds.mvvmarchdemo.network.GetFavouritesUseCase
import kreativemynds.mvvmarchdemo.network.GetPodcastsUseCase
import kreativemynds.mvvmarchdemo.network.UpdateFavourite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for 2 screens.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val getFavourites: GetFavouritesUseCase,
    private val upsertFavourite: UpdateFavourite
) : ViewModel() {

    var podcastListState by mutableStateOf(PodcastListState())
        private set

    var podcastState by mutableStateOf<Podcast?>(null)
        private set

    var favouriteStates = mutableStateListOf<Favourite>()
        private  set

    init {
        getPodcasts()
        getAllFavourites()
    }

    private fun getPodcasts() {
        getPodcastsUseCase().onEach { result ->
            podcastListState = when (result) {
                is BaseResponse.Loading -> {
                    PodcastListState(isLoading = true)
                }

                is BaseResponse.Success -> {
                    PodcastListState(podcasts = result.data ?: emptyList())
                }

                is BaseResponse.Error -> {
                    PodcastListState(error = result.message ?: "Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun selectPodcast(newPodcast: Podcast) {
        podcastState = newPodcast
    }

    private fun getAllFavourites() {
        viewModelScope.launch {
            getFavourites().collectLatest { favourites ->
                favouriteStates.run {
                    clear()
                    addAll(favourites)
                }
            }
        }
    }

    fun updateFavourite(favourite: Favourite) {

        viewModelScope.launch {
            upsertFavourite(favourite = favourite)
            getAllFavourites()
        }
    }
}