package poc.naveen.com.search.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.repository.MainRepository
import poc.naveen.com.search.utils.Resource

/**
 * Created by Naveen on 22-07-2020.
 */

class AlbumViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val albumSearch = MutableLiveData<Resource<AlbumSearch>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchAlbums(album: String) {
        albumSearch.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getAlbumSearch(album)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ albumResult ->
                    albumSearch.postValue(Resource.success(albumResult))
                }, { e ->
                    albumSearch.postValue(Resource.error(e.message.toString(), null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getAlbum(): MutableLiveData<Resource<AlbumSearch>> {
        return albumSearch
    }

}