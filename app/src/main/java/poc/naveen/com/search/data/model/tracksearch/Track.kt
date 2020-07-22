package poc.naveen.com.search.data.model.tracksearch


import com.google.gson.annotations.SerializedName
import poc.naveen.com.search.data.model.Image

/**
 * Created by Naveen on 21-07-2020.
 */
data class Track(
    @SerializedName("artist")
    val artist: String = "",
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("listeners")
    val listeners: String = "",
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("url")
    val url: String = ""
)