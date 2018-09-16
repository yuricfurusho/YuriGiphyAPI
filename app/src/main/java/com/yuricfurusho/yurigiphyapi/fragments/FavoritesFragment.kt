package com.yuricfurusho.yurigiphyapi.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.model.Data
import kotlinx.android.synthetic.main.fragment_favorite_gif_list.*
import org.json.JSONObject
import retrofit2.Response
import java.util.*

class FavoritesFragment : Fragment() {
    private var columnCount = 1
    var favoriteGifList: MutableList<Data> = arrayListOf()
    private var listener: OnListFragmentInteractionListener? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putStringArrayList(FAVORITE_GIF_LIST, favoriteGifList as ArrayList<String>?)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        favoriteGifIds.add("xT77XP9O9da9O04fAI")

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
//        swipeFavoriteGifs.setOnRefreshListener { updateFavoriteList() }

        if (savedInstanceState != null) {
            savedInstanceState.getStringArrayList(FAVORITE_GIF_LIST)
        }
//        updateFavoriteList()
    }

//    fun updateFavoriteList() {
//        swipeFavoriteGifs.isRefreshing = true
//
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//
//
//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.giphy.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//
//        val giphyService = retrofit.create<GiphyService>(GiphyService::class.java!!)
//
//        val listFavoriteGifs: Call<TrendingResponse> = giphyService.listFavoriteGifs(favoriteGifIds.joinToString())
//
//        listFavoriteGifs.enqueue(object : Callback<TrendingResponse?> {
//            override fun onFailure(call: Call<TrendingResponse?>?, t: Throwable?) {
//                val responseText = t!!.message
//                Log.d("GiphyService", responseText)
//                //                call. // TODO
//
//                swipeFavoriteGifs.isRefreshing = false
//            }
//
//            override fun onResponse(call: Call<TrendingResponse?>?, response: Response<TrendingResponse?>?) {
//                val responseText = getRawResponse(response!!)
//                Log.d("GiphyService", responseText)
//
//                if (response != null && response.body() != null) {
//                    favoriteGifList.clear()
//                    favoriteGifList.addAll(response?.body()!!.data)
//                    recyclerFavoriteGifs.adapter.notifyDataSetChanged()
//                }
//
//                swipeFavoriteGifs.isRefreshing = false
//            }
//        })
//
//
//    }

    private fun getRawResponse(response: Response<*>): String {
        var responseText = "RawResponse: \n" + response.toString() + "\n"
        if (response.isSuccessful) {
            responseText = responseText + "response.body: " + if (response.body() != null) response.body()!!.toString() else ""
        } else {
            responseText = responseText + parseError(response)
        }
        return responseText
    }

    private fun parseError(response: Response<*>): String {
        try {
            val jObjError = JSONObject(response.errorBody()!!.string())
            return "errorBody:\n" + jObjError.toString()
        } catch (e: Exception) {
            Log.e(this.javaClass.simpleName, "parseError: " + e.message)
            return "e.getMessage():\n" + e.message
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
//            favoriteGifList.get(indexOf).apply { favorited = !favorited }
            recyclerFavoriteGifs.adapter.notifyItemInserted(indexOf)
        }
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val FAVORITE_GIF_LIST = "favoriteIdList"
        @JvmStatic
        fun newInstance(columnCount: Int) =
                FavoritesFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
