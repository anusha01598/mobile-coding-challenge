package kreativemynds.mvvmarchdemo.network.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kreativemynds.mvvmarchdemo.domain.model.Favourite

@Database(entities = [Favourite::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract val favouriteDao: FavouriteDao
}