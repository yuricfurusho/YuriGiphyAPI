package com.yuricfurusho.yurigiphyapi.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yuricfurusho.yurigiphyapi.fragments.FavoritesFragment
import com.yuricfurusho.yurigiphyapi.fragments.PlaceholderFragment
import com.yuricfurusho.yurigiphyapi.fragments.TrendingFragment

/**
 * Created by Yuri Furusho on 10/09/18.
 */

class GiphyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TAB_TRENDING_VIEWPAGER_POSITION -> TrendingFragment.newInstance(NUMBER_OF_COLUMNS_TAB_TRENDING)
            TAB_FAVORITES_VIEWPAGER_POSITION -> FavoritesFragment.newInstance(NUMBER_OF_COLUMNS_TAB_FAVORITES)
            else -> PlaceholderFragment.newInstance(position + 1)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    companion object {
        const val NUMBER_OF_COLUMNS_TAB_TRENDING = 1
        const val NUMBER_OF_COLUMNS_TAB_FAVORITES = 2
        const val TAB_TRENDING_VIEWPAGER_POSITION = 0
        const val TAB_FAVORITES_VIEWPAGER_POSITION = 1
    }
}