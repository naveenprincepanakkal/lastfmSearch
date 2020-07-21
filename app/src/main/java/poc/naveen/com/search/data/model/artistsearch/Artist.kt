package poc.naveen.com.search.data.model.artistsearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import poc.naveen.com.search.data.model.Image

@SuppressLint("ParcelCreator")
@Parcelize
data class Artist(
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
) : Parcelable