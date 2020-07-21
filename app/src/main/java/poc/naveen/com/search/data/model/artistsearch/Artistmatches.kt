package poc.naveen.com.search.data.model.artistsearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Artistmatches(
    @SerializedName("artist")
    val artist: List<Artist> = listOf()
) : Parcelable