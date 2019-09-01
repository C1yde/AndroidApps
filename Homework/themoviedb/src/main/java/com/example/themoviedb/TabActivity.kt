package com.example.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.themoviedb.ui.main.PagerAdapter
import com.google.android.material.tabs.TabLayout

class TabActivity : AppCompatActivity() {

    @BindView(R.id.view_pager)
    internal var viewPager: ViewPager

    @BindView(R.id.tabs)
    internal lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_activity)
        ButterKnife.bind(this)

        val pagerAdapter = PagerAdapter(this, supportFragmentManager)
        viewPager.adapter = pagerAdapter

        tabs.setupWithViewPager(viewPager)
    }
}