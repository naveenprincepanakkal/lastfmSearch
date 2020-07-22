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
import poc.naveen.com.search.data.model.tracksearch.Track

/**
 * Created by Naveen on 21-07-2020.
 */

class TrackAdapter(private val tracks: ArrayList<Track>, val adapterOnClick: (Track) -> Unit) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private lateinit var context: Context

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tvName
        val artist: TextView = itemView.tvArtist
        val image: ImageView = itemView.imageView
    }

    fun loadTracks(tracks: List<Track>) {
        this.tracks.clear()
        this.tracks.addAll(tracks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        context = parent.context
        return TrackViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.name.text = context.getString(R.string.song) + " : " + track.name
        holder.artist.text = context.getString(R.string.artist) + " : " + track.artist

        Glide.with(context)
            .load(getImageUrl(track.image))
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image);

        holder.itemView.setOnClickListener { v ->
            adapterOnClick(track)
        }
    }

    override fun getItemCount(): Int = tracks.size

    private fun getImageUrl(image: List<Image>): String {
        image.forEach { image -> if (image.size.equals("medium")) return image.text }
        return "";
    }

}