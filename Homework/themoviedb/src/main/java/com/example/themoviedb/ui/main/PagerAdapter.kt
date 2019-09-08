package com.example.themoviedb.ui.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.example.themoviedb.R

@SuppressLint("WrongConstant")
class PagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val title = mContext.getString(TAB_TITLES[position])
        return if (position == 0) {
            WatchlistMovieFragment.newInstance(position, title)
        } else {
            WatchedMovieFragment.newInstance(position, title)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }
}