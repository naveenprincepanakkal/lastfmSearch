package poc.naveen.com.search.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch

import poc.naveen.com.search.data.repository.MainRepository
import poc.naveen.com.search.utils.Resource

/**
 * Created by Naveen on 22-07-2020.
 */

class ArtistViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val artistSearch = MutableLiveData<Resource<ArtistSearch>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchArtist(artist: String) {
        artistSearch.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getArtistSearch(artist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ albumResult ->
                    artistSearch.postValue(Resource.success(albumResult))
                }, { _ ->
                    artistSearch.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getArtist(): MutableLiveData<Resource<ArtistSearch>> {
        return artistSearch
    }

}