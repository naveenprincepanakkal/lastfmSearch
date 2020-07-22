package poc.naveen.com.search.ui.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import poc.naveen.com.search.R
import poc.naveen.com.search.data.model.albumsearch.Album
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.model.artistsearch.Artist
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch
import poc.naveen.com.search.data.model.tracksearch.Track
import poc.naveen.com.search.data.model.tracksearch.TrackSearch
import poc.naveen.com.search.data.network.ApiServiceImpl
import poc.naveen.com.search.ui.adapter.AlbumAdapter
import poc.naveen.com.search.ui.adapter.ArtistAdapter
import poc.naveen.com.search.ui.adapter.TrackAdapter
import poc.naveen.com.search.ui.viewmodel.AlbumViewModel
import poc.naveen.com.search.ui.viewmodel.ArtistViewModel
import poc.naveen.com.search.ui.viewmodel.TrackViewModel
import poc.naveen.com.search.ui.viewmodel.ViewModelFactory
import poc.naveen.com.search.utils.Status
import poc.naveen.com.search.utils.Utils

/**
 * Created by Naveen on 21-07-2020.
 */

class MainActivity : AppCompatActivity() {

    private val searchTypeList = arrayListOf<String>("Album", "Artist", "Song")

    //Search result adapter
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var trackAdapter: TrackAdapter

    //View Models
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var trackViewModel: TrackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initViewModel()
    }

    /**
     * initialise UI
     */
    private fun initUI() {
        spinner.adapter = ArrayAdapter(this, R.layout.simple_list_item, searchTypeList)
        rvSearchList.layoutManager = LinearLayoutManager(this)
        rvSearchList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        initAlbumSearch()

        btnSearch.setOnClickListener() { view ->
            searchView.clearFocus()
            if (searchView.query.toString().trim().isNotEmpty())
                callSearch()
            else
                Toast.makeText(this, "Please enter search", Toast.LENGTH_LONG).show()
        }

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View, position: Int, id: Long
            ) {
                searchView.setQuery("", false)
                when (position) {
                    0 -> initAlbumSearch()
                    1 -> initArtistSearch()
                    2 -> initTrackSearch()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    /**
     * Initialise View Model
     */
    private fun initViewModel() {
        albumViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiServiceImpl()))
            .get(AlbumViewModel::class.java)
        artistViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiServiceImpl()))
            .get(ArtistViewModel::class.java)
        trackViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiServiceImpl()))
            .get(TrackViewModel::class.java)
    }

    /**
     * To find which category search to be called
     */
    private fun callSearch() {
        if (Utils.isNetworkConnectionAvailable(this)) {
            progressBar.visibility = View.VISIBLE
            when (spinner.selectedItemPosition) {
                0 -> loadAlbumSearch()
                1 -> loadArtistSearch()
                2 -> loadTrackSearch()
            }
        } else
            Toast.makeText(this, "NO network available", Toast.LENGTH_LONG).show()
    }

    /**
     * Call webservice fot Album Search
     */
    private fun loadAlbumSearch() {
        albumViewModel.getAlbum().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { albumSearch -> albumSearchResponse(albumSearch) }
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.ERROR -> it.message?.let { errMsg -> handleError(errMsg) }
            }
        })
        albumViewModel.fetchAlbums(searchView.query.toString().trim())
    }

    /**
     * Update adapter with Album search result
     */
    private fun albumSearchResponse(albumSearch: AlbumSearch) {
        progressBar.visibility = View.GONE
        albumAdapter.loadAlbums(albumSearch.results.albummatches.album)
        albumAdapter.notifyDataSetChanged()
        if (albumSearch.results.albummatches.album.isEmpty())
            Toast.makeText(this, "No matching albums", Toast.LENGTH_SHORT).show()
    }

    /**
     * Initialise Album Adapter
     */
    private fun initAlbumSearch() {
        albumAdapter = AlbumAdapter(arrayListOf()) { album -> showAlbumDetails(album) }
        rvSearchList.adapter = albumAdapter
    }

    /**
     * Show Album details
     */
    private fun showAlbumDetails(album: Album) {
        Toast.makeText(this, "Selected Album : " + album.name, Toast.LENGTH_LONG).show()
    }

    /**
     * Call webservice fot Artist Search
     */
    private fun loadArtistSearch() {
        artistViewModel.getArtist().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { artistSearch -> artistSearchResponse(artistSearch) }
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.ERROR -> it.message?.let { errMsg -> handleError(errMsg) }
            }
        })
        artistViewModel.fetchArtist(searchView.query.toString().trim())
    }

    /**
     * Update adapter with Artist search result
     */
    private fun artistSearchResponse(artistSearch: ArtistSearch) {
        progressBar.visibility = View.GONE
        artistAdapter.loadArtist(artistSearch.results.artistmatches.artist)
        artistAdapter.notifyDataSetChanged()
        if (artistSearch.results.artistmatches.artist.isEmpty())
            Toast.makeText(this, "No matching artist", Toast.LENGTH_SHORT).show()
    }

    /**
     * Initialise Artist Adapter
     */
    private fun initArtistSearch() {
        artistAdapter = ArtistAdapter(arrayListOf()) { artist -> showArtistDetails(artist) }
        rvSearchList.adapter = artistAdapter
    }

    /**
     * Show Artist details
     */
    private fun showArtistDetails(artist: Artist) {
        Toast.makeText(this, "Selected Artist : " + artist.name, Toast.LENGTH_LONG).show()
    }

    /**
     * Call webservice fot Track Search
     */
    private fun loadTrackSearch() {
        trackViewModel.getTrack().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { trackSearch -> trackSearchResponse(trackSearch) }
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.ERROR -> it.message?.let { errMsg -> handleError(errMsg) }
            }
        })
        trackViewModel.fetchTrack(searchView.query.toString().trim())
    }

    /**
     * Update adapter with Track search result
     */
    private fun trackSearchResponse(trackSearch: TrackSearch) {
        progressBar.visibility = View.GONE
        trackAdapter.loadTracks(trackSearch.results.trackmatches.track)
        trackAdapter.notifyDataSetChanged()
        if (trackSearch.results.trackmatches.track.isEmpty())
            Toast.makeText(this, "No matching song", Toast.LENGTH_SHORT).show()
    }

    /**
     * Initialise Track Adapter
     */
    private fun initTrackSearch() {
        trackAdapter = TrackAdapter(arrayListOf()) { track -> showTrackDetails(track) }
        rvSearchList.adapter = trackAdapter
    }

    /**
     * Show Track details
     */
    private fun showTrackDetails(track: Track) {
        Toast.makeText(this, "Selected Track : " + track.name, Toast.LENGTH_LONG).show()
    }

    /**
     * Service response error handling
     */
    private fun handleError(errMsg: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, "ERROR : $errMsg", Toast.LENGTH_LONG).show()
    }

}