package ywxt.tools.common.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import ywxt.tools.common.views.CircleImage
import kotlin.random.Random

class DynamicImage(context: Context) : CircleImage(context) {
    
    private val thread = Thread(this::update)
    private val updateSpeed: Long = 50L
    private var flagX=false
    private var flagY=false 
    var speedX:Int
    var speedY :Int

    init {
        //速度初始化
        do {
            speedX=Random.nextInt(-100,100)
        }while (speedX in -20..20)
        do {
            speedY=Random.nextInt(-100,100)
        }while (speedY in -20..20)
        //旋转动画
        val animRotate = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
        animRotate.repeatCount = -1
        animRotate.interpolator = AccelerateDecelerateInterpolator()
        val set = AnimatorSet()
        with(set) {
            duration = 3000
            playTogether(animRotate)
        }
        set.start()
        //移动
        thread.start()
    }

    private fun move() {
        val parent = parent as ViewGroup? ?: return
        if (speedX in -5..5 && speedY in -5..5) {
            this.post {
                parent.removeView(this)
            }
        }
        when {
            this.left < 0 || this.right > parent.width -> {
                if (!flagX){
                    speedX = -speedX
                    flagX=true
                }
            }
            this.top < 0 || this.bottom > parent.height -> {
               if (!flagY){
                   speedY = -speedY
                   flagY=true
               }
            }
            else -> { 
                flagX=false
                flagY=false
            }
        }
        //减慢速度
        speedX += if (speedX > 0) -1 else if (speedX<0) 1 else 0
        speedY += if (speedY > 0) -1 else if (speedY<0) 1 else 0
        this.post {
            val params=this.layoutParams as FrameLayout.LayoutParams
            params.leftMargin+=speedX
            params.topMargin+=speedY
            this.layoutParams=params
        }

    }

    private fun update() {
        while (true) {
            this.move()
            Thread.sleep(updateSpeed)
        }
    }
}