package poc.naveen.com.search.data.model.tracksearch


import com.google.gson.annotations.SerializedName

/**
 * Created by Naveen on 21-07-2020.
 */

data class Results(
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String = "",
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery = OpensearchQuery(),
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String = "",
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String = "",
    @SerializedName("trackmatches")
    val trackmatches: Trackmatches = Trackmatches()
)