package kreativemynds.mvvmarchdemo.domain.repository

import kreativemynds.mvvmarchdemo.network.api.PodcastApi
import kreativemynds.mvvmarchdemo.domain.model.PodcastDto
import javax.inject.Inject

class PodcastRepositoryImpl @Inject constructor(
    private val api: PodcastApi
) : PodcastRepository {
    override suspend fun getPodcasts(): List<PodcastDto> {
        return api.getBestPodcasts().podcasts
    }
}