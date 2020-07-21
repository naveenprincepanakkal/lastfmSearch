package poc.naveen.com.search.data.model.albumsearch


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Results(
    @SerializedName("albummatches")
    val albummatches: Albummatches = Albummatches(),
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String = "",
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery = OpensearchQuery(),
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String = "",
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String = ""
) : Parcelable