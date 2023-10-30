package kreativemynds.mvvmarchdemo.network

import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kreativemynds.mvvmarchdemo.domain.repository.FavouriteRepository
import javax.inject.Inject


class UpdateFavourite @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    suspend operator fun invoke(favourite: Favourite) {
        favouriteRepository.upsert(favourite = favourite)
    }
}