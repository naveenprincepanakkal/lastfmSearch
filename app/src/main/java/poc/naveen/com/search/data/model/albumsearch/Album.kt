package poc.naveen.com.search.data.model.albumsearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import poc.naveen.com.search.data.model.Image

@SuppressLint("ParcelCreator")
@Parcelize
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
) : Parcelable