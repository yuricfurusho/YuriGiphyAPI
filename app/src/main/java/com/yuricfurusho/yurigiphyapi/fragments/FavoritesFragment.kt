package com.yuricfurusho.yurigiphyapi.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuricfurusho.yurigiphyapi.GiphyViewModel
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.adapters.GIFRecyclerViewAdapter
import com.yuricfurusho.yurigiphyapi.model.Data
import kotlinx.android.synthetic.main.fragment_favorite_gif_list.*
import kotlinx.android.synthetic.main.fragment_gif_list.*

class FavoritesFragment : Fragment() {
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    public lateinit var giphyViewModel: GiphyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        giphyViewModel = ViewModelProviders.of(this).get(GiphyViewModel::class.java)

        giphyViewModel.allData.observe(this, Observer { dataList ->
            // Update the cached copy of the words in the adapter.
            dataList?.let { (recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).setDataList(it) }
        })

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

            adapter = com.yuricfurusho.yurigiphyapi.adapters.GIFRecyclerViewAdapter(context, listener, columnCount != 1)
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
        if ((recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).dataList.contains(data)) {
            val indexOf = (recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).dataList.indexOf(data)
            (recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).dataList.remove(data)
            recyclerFavoriteGifs.adapter.notifyItemRemoved(indexOf)
        } else {
            (recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).dataList.add(data)
            val indexOf = (recyclerFavoriteGifs.adapter as GIFRecyclerViewAdapter).dataList.indexOf(data)
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
