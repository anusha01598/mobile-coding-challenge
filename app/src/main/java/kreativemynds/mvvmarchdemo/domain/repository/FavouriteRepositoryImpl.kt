package kreativemynds.mvvmarchdemo.domain.repository

import kreativemynds.mvvmarchdemo.network.room.FavouriteDao
import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteDao
) : FavouriteRepository {

    override fun getFavourites(): Flow<List<Favourite>> {
        return dao.getFavourites()
    }

    override suspend fun upsert(favourite: Favourite) {
        dao.upsert(favourite)
    }
}