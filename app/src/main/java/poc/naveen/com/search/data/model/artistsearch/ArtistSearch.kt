package poc.naveen.com.search.data.model.artistsearch


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class ArtistSearch(
    @SerializedName("results")
    val results: Results = Results()
)