package com.example.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.example.themoviedb.ui.main.PagerAdapter
import kotlinx.android.synthetic.main.tab_activity.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_activity)
        ButterKnife.bind(this)

        val pagerAdapter = PagerAdapter(this, supportFragmentManager)
        viewPager.adapter = pagerAdapter

        tabs.setupWithViewPager(viewPager)
    }
}