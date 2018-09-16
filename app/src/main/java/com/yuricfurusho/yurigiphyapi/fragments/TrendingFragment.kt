package com.yuricfurusho.yurigiphyapi.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuricfurusho.yurigiphyapi.GiphyService
import com.yuricfurusho.yurigiphyapi.R
import com.yuricfurusho.yurigiphyapi.adapters.GIFRecyclerViewAdapter
import com.yuricfurusho.yurigiphyapi.model.Data
import com.yuricfurusho.yurigiphyapi.model.TrendingResponse
import kotlinx.android.synthetic.main.fragment_gif_list.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TrendingFragment : Fragment() {
    private var columnCount = 1
    var gifList: MutableList<Data> = arrayListOf()
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gif_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recyclerTrendingGifs) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            adapter = GIFRecyclerViewAdapter(gifList, listener, columnCount != 1)
        }

        swipeTrendingGifs.setOnRefreshListener { updateTrendingList() }

    }

    private fun updateTrendingList() {
        swipeTrendingGifs.isRefreshing = true

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.giphy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        val giphyService = retrofit.create<GiphyService>(GiphyService::class.java!!)

        val listTrendingGifs: Call<TrendingResponse> = giphyService.listTrendingGifs("20")

        listTrendingGifs.enqueue(object : Callback<TrendingResponse?> {
            override fun onFailure(call: Call<TrendingResponse?>?, t: Throwable?) {
                val responseText = t!!.message
                Log.d("GiphyService", responseText)
//                call. // TODO

                swipeTrendingGifs.isRefreshing = false
            }

            override fun onResponse(call: Call<TrendingResponse?>?, response: Response<TrendingResponse?>?) {
                val responseText = getRawResponse(response!!)
                Log.d("GiphyService", responseText)

                gifList.clear()
                gifList.addAll(response?.body()!!.data)
                recyclerTrendingGifs.adapter.notifyDataSetChanged()
                swipeTrendingGifs.isRefreshing = false
            }
        })

    }

    override fun onResume() {
        super.onResume()


        updateTrendingList()
    }

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
        val index = gifList.indexOf(data)
        recyclerTrendingGifs.adapter.notifyItemChanged(index)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
                TrendingFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
