package ywxt.tools.main

import android.net.Uri
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ywxt.tools.common.activities.BaseActivity
import ywxt.tools.home.HomeFragment
import ywxt.tools.setting.SettingFragment
import ywxt.tools.main.adapters.ToolsFragmentAdapter

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {
   
    private val viewPager by lazy { findViewById<ViewPager>(R.id.tools_main_home_viewpager) }
    private val navigation by lazy {
        findViewById<BottomNavigationView>(R.id.tools_main_home_navigation)
    }
    private val fragmetns= arrayListOf(
        HomeFragment(),
        SettingFragment()
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_main_activity_main)
        viewPager.adapter=ToolsFragmentAdapter(this.supportFragmentManager,fragmetns)
        viewPager.addOnPageChangeListener(this)
        navigation.setOnNavigationItemSelectedListener { 
            viewPager.currentItem=it.order
            return@setOnNavigationItemSelectedListener true
        }
        
    }
    override fun onPageScrollStateChanged(state: Int) {
        
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        
    }

    override fun onPageSelected(position: Int) {
        navigation.menu.getItem(position).isChecked=true
    }

}

