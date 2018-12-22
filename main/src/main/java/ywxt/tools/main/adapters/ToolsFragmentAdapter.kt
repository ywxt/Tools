package ywxt.tools.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ToolsFragmentAdapter(
    manager: FragmentManager,
    private val fragments:ArrayList<Fragment>
):FragmentPagerAdapter(manager) {
    
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}