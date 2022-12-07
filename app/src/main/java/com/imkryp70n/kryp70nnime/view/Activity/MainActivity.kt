package com.imkryp70n.kryp70nnime.view.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.view.fragments.discover.DiscoveryFragment
import com.imkryp70n.kryp70nnime.view.fragments.history.HistoryFragment
import com.imkryp70n.kryp70nnime.view.fragments.search.SearchFragment
import com.imkryp70n.kryp70nnime.view.fragments.setting.SettingFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    val TAG = "CONSOLE"


    private lateinit var bottomBar: AnimatedBottomBar
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()

        bottomBarLogic()
    }


    private fun setupView(){
        bottomBar = findViewById(R.id.animated_bottom_bar)
        viewPager2 = findViewById(R.id.viewPager2)
        viewPager2.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)
    }

    private fun bottomBarLogic(){

        bottomBar.setupWithViewPager2(viewPager2)
        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {

            }

        })
    }

    class ViewPager2Adapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DiscoveryFragment()
                1 -> SearchFragment()
                2 -> HistoryFragment()
                3 -> SettingFragment()
                else -> DiscoveryFragment()
            }
        }
    }
}