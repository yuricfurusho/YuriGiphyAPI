package com.yuricfurusho.yurigiphyapi

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.yuricfurusho.yurigiphyapi.adapters.GiphyPagerAdapter
import com.yuricfurusho.yurigiphyapi.fragments.FavoritesFragment
import com.yuricfurusho.yurigiphyapi.fragments.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {
    override fun onAddToFavorite(itemID: String) {

        val favoritesFrag: FavoritesFragment? = giphyPagerAdapter?.getItem(1) as FavoritesFragment?

        favoritesFrag?.updateFavoriteList(itemID)

//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var giphyPagerAdapter: GiphyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        giphyPagerAdapter = GiphyPagerAdapter(supportFragmentManager)
        container.adapter = giphyPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

}
