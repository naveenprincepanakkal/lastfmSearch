package poc.naveen.com.search.data.network

import io.reactivex.Single
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch
import poc.naveen.com.search.data.model.tracksearch.TrackSearch
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Naveen on 21-07-2020
 */

interface ApiService {

    @GET("?method=album.search" + "&api_key=" + ApiProvider.API_KEY + ApiProvider.FORMAT_JSON)
    fun getAlbumSearch(@Query("album") album: String): Single<AlbumSearch>

    @GET("?method=artist.search" + "&api_key=" + ApiProvider.API_KEY + ApiProvider.FORMAT_JSON)
    fun getArtistSearch(@Query("artist") artist: String): Single<ArtistSearch>

    @GET("?method=track.search" + "&api_key=" + ApiProvider.API_KEY + ApiProvider.FORMAT_JSON)
    fun getTrackSearch(@Query("track") track: String): Single<TrackSearch>

}