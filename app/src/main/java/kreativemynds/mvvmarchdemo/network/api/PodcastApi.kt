package kreativemynds.mvvmarchdemo.network.api

import kreativemynds.mvvmarchdemo.domain.model.BestPodcast
import retrofit2.http.GET


interface PodcastApi {
    @GET("best_podcasts")
    suspend fun getBestPodcasts(): BestPodcast
}