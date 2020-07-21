package poc.naveen.com.search.data.model.artistsearch


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Results(
    @SerializedName("artistmatches")
    val artistmatches: Artistmatches = Artistmatches(),
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String = "",
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery = OpensearchQuery(),
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String = "",
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String = ""
) : Parcelable