package com.yuricfurusho.yurigiphyapi.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.fragments.OnListFragmentInteractionListener
import com.yuricfurusho.yurigiphyapi.model.Data

class GIFRecyclerViewAdapter(
        private val mGifObjectList: MutableList<Data>,
        private val mListener: OnListFragmentInteractionListener?,
        private val isGrid: Boolean)
    : RecyclerView.Adapter<GIFRecyclerViewAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (isGrid ) R.layout.adapter_gif_favorite else R.layout.adapter_gif
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Data = mGifObjectList[position]
        if (data.favorited) {
            holder.favButton.setImageResource(R.drawable.ic_favorite_selector_checked)
        } else {
            holder.favButton.setImageResource(R.drawable.ic_favorite_selector_unchecked)
        }


        Glide.with(holder.itemView)
                .load(data.images.fixedHeightDownsampled.url)
                .into(holder.imageViewGif)

        with(holder.favButton) {
            setOnClickListener {
                data.apply { favorited = !favorited }
                mListener?.onAddToFavorite(data)
            }
        }
    }

    override fun getItemCount(): Int = mGifObjectList.size

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.imageViewGif.layout(0, 0, 0, 0)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val favButton: ImageView = mView.findViewById(R.id.favButton)
        val imageViewGif: ImageView = mView.findViewById(R.id.imageViewGif)
    }
}
