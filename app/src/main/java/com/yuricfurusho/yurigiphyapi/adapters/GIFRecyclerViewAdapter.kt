package com.yuricfurusho.yurigiphyapi.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.fragments.TrendingFragment.OnListFragmentInteractionListener
import com.yuricfurusho.yurigiphyapi.model.GifObject

class GIFRecyclerViewAdapter(
        private val mGifObjectList: List<GifObject>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<GIFRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as GifObject
            mListener?.onAddToFavorite(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mGifObjectList[position]

        if (position % 2 ==0) {
            Glide.with(holder.itemView).load("http://lorempixel.com/400/200/food/").into(holder.imageViewGif)
        } else {
            Glide.with(holder.itemView)
                    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Rotating_earth_%28large%29.gif/200px-Rotating_earth_%28large%29.gif")
                    .into(holder.imageViewGif)
        }

        with(holder.imageViewGif) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mGifObjectList.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageViewGif: ImageView = mView.findViewById(R.id.imageViewGif)
    }
}
