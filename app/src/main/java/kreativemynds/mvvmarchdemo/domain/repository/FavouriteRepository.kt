package kreativemynds.mvvmarchdemo.domain.repository

import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getFavourites(): Flow<List<Favourite>>
    suspend fun upsert(favourite: Favourite)
}