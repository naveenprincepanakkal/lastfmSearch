package poc.naveen.com.search.data.model.albumsearch


import com.google.gson.annotations.SerializedName
import poc.naveen.com.search.data.model.Image

/**
 * Created by Naveen on 21-07-2020.
 */

data class Album(
    @SerializedName("artist")
    val artist: String = "",
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("url")
    val url: String = ""
)