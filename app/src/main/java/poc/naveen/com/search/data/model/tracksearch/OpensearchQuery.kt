package poc.naveen.com.search.data.model.tracksearch


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class OpensearchQuery(
    @SerializedName("role")
    val role: String = "",
    @SerializedName("startPage")
    val startPage: String = "",
    @SerializedName("#text")
    val text: String = ""
)