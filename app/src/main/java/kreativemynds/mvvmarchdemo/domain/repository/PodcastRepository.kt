package kreativemynds.mvvmarchdemo.domain.repository

import kreativemynds.mvvmarchdemo.domain.model.PodcastDto

interface PodcastRepository {
    suspend fun getPodcasts(): List<PodcastDto>
}