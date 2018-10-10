package com.yuricfurusho.yurigiphyapi.adapters


import android.content.Context
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
        context: Context,
        private val mListener: OnListFragmentInteractionListener?,
        private val isGrid: Boolean)
    : RecyclerView.Adapter<GIFRecyclerViewAdapter.DataViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    public var dataList:MutableList<Data> = mutableListOf<Data>()


    override fun getItemViewType(position: Int): Int {
        return if (isGrid) R.layout.adapter_gif_favorite else R.layout.adapter_gif
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = inflater.inflate(viewType, parent, false)

        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val current = dataList[position]
        if (current.favorited) {
            holder.favButton.setImageResource(R.drawable.ic_favorite_selector_checked)
        } else {
            holder.favButton.setImageResource(R.drawable.ic_favorite_selector_unchecked)
        }


        Glide.with(holder.itemView)
                .load(current.images?.fixedHeightDownsampled?.url)
                .into(holder.imageViewGif)

//
//        Glide.with(holder.itemView)
//                .load(data.images.fixedHeightDownsampled.url)
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
//                    }
//                    override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
//                        Log.d("das", "OnResourceReady")
//                        //do something when picture already loaded
//                        return false
//                    }
//                })
//                .into(holder.imageViewGif)

        with(holder.favButton) {
            setOnClickListener {
                current.apply { favorited = !favorited }
                mListener?.onAddToFavorite(current)
            }
        }
    }

    internal fun setDataList(dataList: MutableList<Data>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    override fun onViewRecycled(holder: DataViewHolder) {
        super.onViewRecycled(holder)
        holder.imageViewGif.layout(0, 0, 0, 0)
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val favButton: ImageView = itemView.findViewById(R.id.favButton)
        val imageViewGif: ImageView = itemView.findViewById(R.id.imageViewGif)
    }
}
