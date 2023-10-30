package kreativemynds.mvvmarchdemo.network.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

/**
 * Room DAO to access and track state of "favourite" stored in DB
 */
@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(favourite: Favourite)

    @Query("SELECT * FROM FAVOURITE")
    fun getFavourites(): Flow<List<Favourite>>
}