package com.app.fr.leboncoin.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.fr.leboncoin.R
import com.app.fr.leboncoin.core.BaseActivity
import com.app.fr.leboncoin.di.GlobalInjectorModule
import com.app.fr.leboncoin.ui.home.adapter.AlbumListAdapter
import com.app.fr.leboncoin.ui.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var albumListAdapter: AlbumListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent
            .builder()
            .globalInjectorModule(GlobalInjectorModule(this))
            .build()
            .inject(this)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        if (isConnected()) {
            homeViewModel.getAlbum()
        }
        albumListAdapter = AlbumListAdapter(listOf(), this)
        rv_album.layoutManager = GridLayoutManager(this, 2)
        rv_album.adapter = albumListAdapter
        homeViewModel.onListAlbumLoaded().observe(this, Observer { response ->
            albumListAdapter.setList(response)

            Log.d("RESPONSE", response[0].url)
        })
    }

    private fun isConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}
