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
import poc.naveen.com.search.data.model.albumsearch.Album

/**
 * Created by Naveen on 21-07-2020.
 */

class AlbumAdapter(private val albums: ArrayList<Album>, val adapterOnClick: (Album) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private lateinit var context: Context

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tvName
        val artist: TextView = itemView.tvArtist
        val image: ImageView = itemView.imageView
    }

    fun loadAlbums(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        context = parent.context
        return AlbumViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.name.text = context.getString(R.string.album) + " : " + album.name
        holder.artist.text = context.getString(R.string.artist) + " : " + album.artist

        Glide.with(context)
            .load(getImageUrl(album.image))
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image);

        holder.itemView.setOnClickListener { v ->
            adapterOnClick(album)
        }
    }

    override fun getItemCount(): Int = albums.size

    private fun getImageUrl(image: List<Image>): String {
        image.forEach { image -> if (image.size.equals("medium")) return image.text }
        return "";
    }

}