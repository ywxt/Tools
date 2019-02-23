package ywxt.tools.home

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ywxt.tools.common.activities.ACTION_ACTIVITY_AUTO_REPLY
import ywxt.tools.common.activities.ACTION_ACTIVITY_CLICKING_GAME
import ywxt.tools.common.activities.ACTION_ACTIVITY_IP_ADDRESS
import ywxt.tools.common.fragments.BaseFragment
import ywxt.tools.common.models.ClickingGameData
import ywxt.tools.home.adapters.ToolViewAdapter
import ywxt.tools.home.models.ToolModel

class HomeFragment: BaseFragment(){
    private val tools by lazy {  arrayListOf(
        ToolModel(
            this.context!!.getString(ywxt.tools.common.R.string.tools_common_activity_autoreply),
            ContextCompat.getDrawable(this.context!!, ywxt.tools.common.R.drawable.tools_common_icon_autoreply)!!,
            ACTION_ACTIVITY_AUTO_REPLY,
            null
        ),
        ToolModel(
            getString(ywxt.tools.common.R.string.tools_common_activity_ipaddress),
            ContextCompat.getDrawable(this.context!!,ywxt.tools.common.R.drawable.tools_common_icon_ipaddress)!!,
            ACTION_ACTIVITY_IP_ADDRESS,
            null
        ),
        ToolModel(
            getString(ywxt.tools.common.R.string.tools_common_activity_clickinggame),
            ContextCompat.getDrawable(this.context!!,ywxt.tools.common.R.drawable.tools_common_icon_clicking_game)!!,
            ACTION_ACTIVITY_CLICKING_GAME,
            ClickingGameData("点击试试")
        )
    )}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.tools_home_fragment_home,container,false)
        val recyclerView=view.findViewById<RecyclerView>(R.id.tool_home_list)
        recyclerView.layoutManager=GridLayoutManager(this.context,4)
        recyclerView.adapter=ToolViewAdapter(this::onItemClick,tools)
        return view
    }
    private fun onItemClick(v:View,action: String,data:Parcelable?){
        val i=Intent(action)
        data?.let {  i.putExtra("data",it)}
        if (i.resolveActivity(this.context!!.packageManager)!=null){
            startActivity(i)
        }
    }
}