package poc.naveen.com.search.data.model.albumsearch


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class Albummatches(
    @SerializedName("album")
    val album: List<Album> = listOf()
)