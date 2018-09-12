package com.yuricfurusho.yurigiphyapi.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.fragments.TrendingFragment.OnListFragmentInteractionListener
import com.yuricfurusho.yurigiphyapi.model.Data

class GIFRecyclerViewAdapter(
        private val mGifObjectList: List<Data>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<GIFRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            mListener?.onAddToFavorite(v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Data = mGifObjectList[position]

        Glide.with(holder.itemView).load(item.images.fixedHeightDownsampled.url).into(holder.imageViewGif)

        with(holder.imageViewGif) {
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mGifObjectList.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageViewGif: ImageView = mView.findViewById(R.id.imageViewGif)
    }
}
