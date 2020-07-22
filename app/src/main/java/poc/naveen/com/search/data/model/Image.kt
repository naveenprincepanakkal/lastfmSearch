package poc.naveen.com.search.data.model


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class Image(
    @SerializedName("size")
    val size: String = "",
    @SerializedName("#text")
    val text: String = ""
)