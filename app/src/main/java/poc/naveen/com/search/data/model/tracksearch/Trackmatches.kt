package poc.naveen.com.search.data.model.tracksearch


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class Trackmatches(
    @SerializedName("track")
    val track: List<Track> = listOf()
)