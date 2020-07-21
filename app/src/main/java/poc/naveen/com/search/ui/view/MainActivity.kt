package poc.naveen.com.search.ui.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import poc.naveen.com.search.R
import poc.naveen.com.search.data.model.albumsearch.AlbumSearch
import poc.naveen.com.search.data.model.artistsearch.ArtistSearch
import poc.naveen.com.search.data.model.tracksearch.TrackSearch
import poc.naveen.com.search.data.network.ApiServiceImpl
import poc.naveen.com.search.ui.adapter.AlbumAdapter
import poc.naveen.com.search.ui.adapter.ArtistAdapter
import poc.naveen.com.search.ui.adapter.TrackAdapter


class MainActivity : AppCompatActivity() {

    private var apiServiceImpl = ApiServiceImpl()
    private val compositeDisposable = CompositeDisposable()
    private val searchTypeList = arrayListOf<String>("Album", "Artist", "Song")

    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var trackAdapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

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

    private fun callSearch() {
        progressBar.visibility = View.VISIBLE
        when (spinner.selectedItemPosition) {
            0 -> loadAlbumSearch()
            1 -> loadArtistSearch()
            2 -> loadTrackSearch()
        }
    }

    private fun loadAlbumSearch() {
        compositeDisposable.add(
            apiServiceImpl.getAlbumSearch(searchView.query.toString().trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::albumSearchResponse, this::handleError)
        )
    }

    private fun albumSearchResponse(albumSearch: AlbumSearch) {
        progressBar.visibility = View.GONE
        albumAdapter.loadAlbums(albumSearch.results.albummatches.album)
        albumAdapter.notifyDataSetChanged()
    }

    private fun initAlbumSearch() {
        albumAdapter = AlbumAdapter(arrayListOf())
        rvSearchList.adapter = albumAdapter
    }

    private fun loadArtistSearch() {
        compositeDisposable.add(
            apiServiceImpl.getArtistSearch(searchView.query.toString().trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::artistSearchResponse, this::handleError)
        )
    }

    private fun artistSearchResponse(artistSearch: ArtistSearch) {
        progressBar.visibility = View.GONE
        artistAdapter.loadArtist(artistSearch.results.artistmatches.artist)
        artistAdapter.notifyDataSetChanged()
    }

    private fun initArtistSearch() {
        artistAdapter = ArtistAdapter(arrayListOf())
        rvSearchList.adapter = artistAdapter
    }

    private fun loadTrackSearch() {
        compositeDisposable.add(
            apiServiceImpl.getTrackSearch(searchView.query.toString().trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::trackSearchResponse, this::handleError)
        )
    }

    private fun trackSearchResponse(trackSearch: TrackSearch) {
        progressBar.visibility = View.GONE
        trackAdapter.loadTracks(trackSearch.results.trackmatches.track)
        trackAdapter.notifyDataSetChanged()
    }

    private fun initTrackSearch() {
        trackAdapter = TrackAdapter(arrayListOf())
        rvSearchList.adapter = trackAdapter
    }

    private fun handleError(e: Throwable) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, "ERROR : " + e.message, Toast.LENGTH_LONG).show()
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}