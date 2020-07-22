package poc.naveen.com.search.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.album_list_item.view.*
import poc.naveen.com.search.R
import poc.naveen.com.search.data.model.Image
import poc.naveen.com.search.data.model.artistsearch.Artist

/**
 * Created by Naveen on 21-07-2020.
 */

class ArtistAdapter(private val artist: ArrayList<Artist>, val adapterOnClick: (Artist) -> Unit) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    private lateinit var context: Context

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tvName
        val artist: TextView = itemView.tvArtist
        val image: ImageView = itemView.imageView
    }

    fun loadArtist(artist: List<Artist>) {
        this.artist.clear()
        this.artist.addAll(artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        context = parent.context
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artist[position]
        holder.name.visibility = View.GONE
        holder.artist.text = context.getString(R.string.artist) + " : " + artist.name

        Glide.with(context)
            .load(getImageUrl(artist.image))
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image);

        holder.itemView.setOnClickListener { v ->
            adapterOnClick(artist)
        }
    }

    override fun getItemCount(): Int = artist.size

    private fun getImageUrl(image: List<Image>): String {
        image.forEach { image -> if (image.size.equals("medium")) return image.text }
        return "";
    }

}