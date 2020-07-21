package poc.naveen.com.search.data.model.tracksearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class OpensearchQuery(
    @SerializedName("role")
    val role: String = "",
    @SerializedName("startPage")
    val startPage: String = "",
    @SerializedName("#text")
    val text: String = ""
) : Parcelable