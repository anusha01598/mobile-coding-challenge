package kreativemynds.mvvmarchdemo.di

import android.app.Application
import androidx.room.Room
import kreativemynds.mvvmarchdemo.domain.common.Constants
import kreativemynds.mvvmarchdemo.network.room.FavouriteDao
import kreativemynds.mvvmarchdemo.network.room.FavouriteDatabase
import kreativemynds.mvvmarchdemo.network.api.PodcastApi
import kreativemynds.mvvmarchdemo.domain.repository.FavouriteRepositoryImpl
import kreativemynds.mvvmarchdemo.domain.repository.PodcastRepositoryImpl
import kreativemynds.mvvmarchdemo.domain.repository.FavouriteRepository
import kreativemynds.mvvmarchdemo.domain.repository.PodcastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency Injection modules
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): PodcastApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PodcastApi::class.java)
    }

    @Provides
    @Singleton
    fun providePodcastRepository(api: PodcastApi): PodcastRepository {
        return PodcastRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideFavouriteDatabase(application: Application): FavouriteDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = FavouriteDatabase::class.java,
            name = Constants.FAVOURITE_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavouriteDao(favouriteDatabase: FavouriteDatabase): FavouriteDao {
        return favouriteDatabase.favouriteDao
    }

    @Provides
    @Singleton
    fun provideFavouriteRepository(dao: FavouriteDao): FavouriteRepository {
        return FavouriteRepositoryImpl(dao = dao)
    }
}