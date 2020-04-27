package com.app.fr.leboncoin.ui.home.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.fr.leboncoin.R
import com.app.fr.leboncoin.album.AlbumEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_list_album.view.*
import java.net.URL


class AlbumListAdapter(var items: List<AlbumEntity>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items_list_album, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text = items[position].title

        Picasso.get()
            .load(items[position].url)
            .into(holder.iv_album)

    }

    fun setList(list: List<AlbumEntity>) {
        items = list
        notifyDataSetChanged()
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tv_title = view.tv_title
    val iv_album= view.iv_picture
}




