package ywxt.tools.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ywxt.tools.home.R
import ywxt.tools.home.models.ToolModel

class ToolViewAdapter(
    val click:(view:View, action:String)->Unit,
    private val dataList: ArrayList<ToolModel>
) : RecyclerView.Adapter<ToolViewAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tools_home_adapter_tool,
            parent,false
        )
        view.setOnClickListener { 
            click(it,it.tag as String)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text= dataList[position].text
        holder.image.setImageDrawable(dataList[position].image)
        holder.itemView.tag=dataList[position].action
    }

    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView= view.findViewById<TextView>(R.id.tool_text)!!
        val image= view.findViewById<ImageView>(R.id.tool_image)!!
    }
}