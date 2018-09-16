package com.yuricfurusho.yurigiphyapi

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.yuricfurusho.yurigiphyapi.adapters.GiphyPagerAdapter
import com.yuricfurusho.yurigiphyapi.fragments.FavoritesFragment
import com.yuricfurusho.yurigiphyapi.fragments.OnListFragmentInteractionListener
import com.yuricfurusho.yurigiphyapi.fragments.TrendingFragment
import com.yuricfurusho.yurigiphyapi.model.Data
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {
    override fun onAddToFavorite(data: Data) {

        val trendingFrag: TrendingFragment = supportFragmentManager
                .findFragmentByTag("android:switcher:" + R.id.viewPagerMain + ":" + GiphyPagerAdapter.TAB_TRENDING_VIEWPAGER_POSITION) as TrendingFragment
        val favoritesFrag: FavoritesFragment = supportFragmentManager
                .findFragmentByTag("android:switcher:" + R.id.viewPagerMain + ":" + GiphyPagerAdapter.TAB_FAVORITES_VIEWPAGER_POSITION) as FavoritesFragment
        trendingFrag?.updateFavoriteList(data)
        favoritesFrag?.updateFavoriteList(data)
    }

    private var giphyPagerAdapter: GiphyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        giphyPagerAdapter = GiphyPagerAdapter(supportFragmentManager)
        viewPagerMain.adapter = giphyPagerAdapter

        viewPagerMain.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPagerMain))

    }

}
