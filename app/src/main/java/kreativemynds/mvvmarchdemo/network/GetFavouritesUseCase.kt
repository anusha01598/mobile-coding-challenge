package kreativemynds.mvvmarchdemo.network

import kreativemynds.mvvmarchdemo.domain.model.Favourite
import kreativemynds.mvvmarchdemo.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    operator fun invoke(): Flow<List<Favourite>> {
        return favouriteRepository.getFavourites()
    }
}