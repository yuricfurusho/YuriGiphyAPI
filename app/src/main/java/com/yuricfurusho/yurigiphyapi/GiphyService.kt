package com.yuricfurusho.yurigiphyapi

import com.yuricfurusho.yurigiphyapi.model.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Yuri Furusho on 11/09/18.
 */
interface GiphyService {
//    companion object {
//        val API_KEY = "2DTSEy6urPd1dJIa6dh3yFuC2u9Yo3B2"
//    }

    //    @GET("v1/gifs/trending?api_key=" + API_KEY) TODO
    @GET("v1/gifs/trending?api_key=2DTSEy6urPd1dJIa6dh3yFuC2u9Yo3B2")
    fun listTrendingGifs(@Query("limit") limit: String): Call<TrendingResponse>

    //    @GET("v1/gifs/trending?api_key=" + API_KEY) TODO
    @GET("/v1/gifs?api_key=2DTSEy6urPd1dJIa6dh3yFuC2u9Yo3B2")
    fun listFavoriteGifs(@Query("ids") ids: String): Call<TrendingResponse>
}