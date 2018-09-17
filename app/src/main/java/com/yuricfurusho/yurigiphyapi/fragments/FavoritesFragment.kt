package com.yuricfurusho.yurigiphyapi.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.model.Data
import kotlinx.android.synthetic.main.fragment_favorite_gif_list.*

class FavoritesFragment : Fragment() {
    private var columnCount = 1
    var favoriteGifList: MutableList<Data> = arrayListOf()
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_gif_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recyclerFavoriteGifs) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL)
            }

            adapter = com.yuricfurusho.yurigiphyapi.adapters.GIFRecyclerViewAdapter(favoriteGifList, listener, columnCount != 1)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun updateFavoriteList(data: Data) {
        if (favoriteGifList.contains(data)) {
            val indexOf = favoriteGifList.indexOf(data)
            favoriteGifList.remove(data)
            recyclerFavoriteGifs.adapter.notifyItemRemoved(indexOf)
        } else {
            favoriteGifList.add(data)
            val indexOf = favoriteGifList.indexOf(data)
            recyclerFavoriteGifs.adapter.notifyItemInserted(indexOf)
        }
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
                FavoritesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
