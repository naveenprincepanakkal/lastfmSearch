package poc.naveen.com.search.data.model.artistsearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ArtistSearch(
    @SerializedName("results")
    val results: Results = Results()
) : Parcelable