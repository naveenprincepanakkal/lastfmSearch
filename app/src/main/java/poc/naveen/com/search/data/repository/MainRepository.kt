package poc.naveen.com.search.data.repository

import io.reactivex.Single
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch
import poc.naveen.com.search.data.model.tracksearch.TrackSearch
import poc.naveen.com.search.data.network.ApiServiceImpl

/**
 * Created by Naveen on 22-07-2020.
 */

class MainRepository(private val apiServiceImpl: ApiServiceImpl) {

    fun getAlbumSearch(album: String): Single<AlbumSearch> {
        return apiServiceImpl.getAlbumSearch(album)
    }

    fun getArtistSearch(artist: String): Single<ArtistSearch> {
        return apiServiceImpl.getArtistSearch(artist)
    }

    fun getTrackSearch(track: String): Single<TrackSearch> {
        return apiServiceImpl.getTrackSearch(track)
    }
}