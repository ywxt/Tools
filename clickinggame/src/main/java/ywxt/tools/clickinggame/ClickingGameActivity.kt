package ywxt.tools.clickinggame

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import ywxt.tools.common.activities.BaseActivity
import ywxt.tools.common.views.DynamicImage
import kotlin.random.Random

class ClickingGameActivity:BaseActivity() {
    
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_clickinggame_activity_clicking_game)
        setSupportActionBar(toolbar)
        val view =findViewById<FrameLayout>(R.id.tools_clickinggame_layout_frame)
       
        view.setOnTouchListener { _, event ->
            if (event.action!= MotionEvent.ACTION_DOWN)
                return@setOnTouchListener false
            val image = DynamicImage(this)
            image.circleImage = BitmapFactory.decodeResource(this.resources, getImg(Random.nextInt(4)))
            val param = FrameLayout.LayoutParams(200, 200)
            param.topMargin = (event.y-image.height/2).toInt()
            param.leftMargin = (event.x-image.width/2).toInt()
            view.addView(image, param)
            true
        }
        enableBack=true
    }

   

    private fun getImg(i:Int):Int{
        return when(i){
            1->R.drawable.img_1
            2->R.drawable.img_2
            else -> R.drawable.img_3
        }
    }
}