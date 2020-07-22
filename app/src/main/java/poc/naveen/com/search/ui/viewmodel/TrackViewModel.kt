package poc.naveen.com.search.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import poc.naveen.com.search.data.model.tracksearch.TrackSearch

import poc.naveen.com.search.data.repository.MainRepository
import poc.naveen.com.search.utils.Resource

/**
 * Created by Naveen on 22-07-2020.
 */

class TrackViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val trackSearch = MutableLiveData<Resource<TrackSearch>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchTrack(track: String) {
        trackSearch.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getTrackSearch(track)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ trackResult ->
                    trackSearch.postValue(Resource.success(trackResult))
                }, { _ ->
                    trackSearch.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getTrack(): MutableLiveData<Resource<TrackSearch>> {
        return trackSearch
    }

}