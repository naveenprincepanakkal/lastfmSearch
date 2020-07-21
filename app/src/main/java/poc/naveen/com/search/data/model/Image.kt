package poc.naveen.com.search.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Image(
    @SerializedName("size")
    val size: String = "",
    @SerializedName("#text")
    val text: String = ""
) : Parcelable