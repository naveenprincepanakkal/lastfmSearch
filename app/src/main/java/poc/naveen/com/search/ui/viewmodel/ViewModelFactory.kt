package poc.naveen.com.search.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import poc.naveen.com.search.data.network.ApiServiceImpl
import poc.naveen.com.search.data.repository.MainRepository

/**
 * Created by Naveen on 22-07-2020.
 */

class ViewModelFactory(private val apiServiceImpl: ApiServiceImpl) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            return AlbumViewModel(MainRepository(apiServiceImpl)) as T
        }
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            return ArtistViewModel(MainRepository(apiServiceImpl)) as T
        }
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return TrackViewModel(MainRepository(apiServiceImpl)) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}