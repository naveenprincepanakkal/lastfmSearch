package poc.naveen.com.search.data.network

import io.reactivex.Single
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch
import poc.naveen.com.search.data.model.tracksearch.TrackSearch

class ApiServiceImpl : ApiService {

    private val retrofit = ApiProvider.create()

    override fun getAlbumSearch(album: String): Single<AlbumSearch> {
        return retrofit.getAlbumSearch(album)
    }

    override fun getArtistSearch(artist: String): Single<ArtistSearch> {
        return retrofit.getArtistSearch(artist)
    }

    override fun getTrackSearch(track: String): Single<TrackSearch> {
        return retrofit.getTrackSearch(track)
    }

}