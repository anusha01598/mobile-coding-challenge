package kreativemynds.mvvmarchdemo.network

import kreativemynds.mvvmarchdemo.domain.common.BaseResponse
import kreativemynds.mvvmarchdemo.domain.model.toPodcast
import kreativemynds.mvvmarchdemo.domain.model.Podcast
import kreativemynds.mvvmarchdemo.domain.repository.PodcastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetPodcastsUseCase @Inject constructor(
    private val repository: PodcastRepository
) {
    operator fun invoke(): Flow<BaseResponse<List<Podcast>>> = flow {
        try {
            emit(BaseResponse.Loading())
            val podcasts = repository.getPodcasts().map { podcastDto ->
                //Cast the DTO network to actual Data set that only holds the app needs
                podcastDto.toPodcast()
            }
            emit(BaseResponse.Success(podcasts))

        } catch (e: HttpException) {
            emit(
                BaseResponse.Error(
                    message = e.localizedMessage ?: "Unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                BaseResponse.Error(
                    message = e.localizedMessage ?: "IOException occurred"
                )
            )
        }
    }
}