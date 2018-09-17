package com.yuricfurusho.yurigiphyapi

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.yuricfurusho.yurigiphyapi.adapters.GiphyPagerAdapter
import com.yuricfurusho.yurigiphyapi.fragments.FavoritesFragment
import com.yuricfurusho.yurigiphyapi.fragments.OnListFragmentInteractionListener
import com.yuricfurusho.yurigiphyapi.fragments.TrendingFragment
import com.yuricfurusho.yurigiphyapi.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.R.menu
import android.content.Context
import android.support.v7.widget.SearchView
import android.view.MenuInflater





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

        // Get the intent, verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            doMySearch(query)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // Get the intent, verify the action and get the query
        if (intent != null) {
            if (Intent.ACTION_SEARCH == intent.action) {
                val query = intent.getStringExtra(SearchManager.QUERY)
                doMySearch(query)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false) // Do not iconify the widget; expand it by default

        return true
    }

    private fun doMySearch(query: String?) {
        val trendingFrag: TrendingFragment = supportFragmentManager
                .findFragmentByTag("android:switcher:" + R.id.viewPagerMain + ":" + GiphyPagerAdapter.TAB_TRENDING_VIEWPAGER_POSITION) as TrendingFragment
        trendingFrag?.searchGif(query)
    }

}
