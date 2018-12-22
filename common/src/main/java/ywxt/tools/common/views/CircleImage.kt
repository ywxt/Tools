package ywxt.tools.common.views


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ywxt.tools.common.R
import kotlin.math.min

/**
 *取得控件大小
 */
fun View.getSize(measureSpec: Int, default: Int): Int {
    val mode = View.MeasureSpec.getMode(measureSpec)
    val size = View.MeasureSpec.getSize(measureSpec)
    return when (mode) {
        View.MeasureSpec.UNSPECIFIED -> {//如果没有指定大小，就设置为默认大小
            default
        }
        View.MeasureSpec.AT_MOST -> {//测量模式是最大取值为size
            min(size,default)//这儿踩坑,应该取小的一个，size是布局大小
        }
        View.MeasureSpec.EXACTLY -> {//如果是固定的大小，那就不要去改变它
            size
        }
        else -> {
            default
        }
    }
}
/**
 * 圆形图片
 */
open class CircleImage(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private var rect=RectF(0f,0f,0.toFloat(),0.toFloat())
    var circleImage: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        set(value) {
            field = getCircle(value)
            
        }


    constructor(context: Context) : this(context, null)

    init {
        if (attrs != null) {
            //解析Xml属性
            val arrays = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleImage, 0, 0)
            val id = arrays.getResourceId(R.styleable.CircleImage_imageSrc, R.drawable.tools_common_icon_circle_image)
            circleImage = BitmapFactory.decodeResource(context.resources, id)
            arrays.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(circleImage,null,rect,paint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = getSize(widthMeasureSpec, circleImage.width)
        val height = getSize(heightMeasureSpec, circleImage.width)
        setMeasuredDimension(width,height)
        rect= RectF(0.toFloat(),0.toFloat(),width.toFloat(),height.toFloat())

    }

    

    /**
     * 把bitmap转成圆形
     */
    private fun getCircle(bitmap: Bitmap): Bitmap {
        val bm = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        val p = Paint()
        val rect=RectF(0F,0F,bitmap.width.toFloat(),bitmap.height.toFloat())
        canvas.drawOval(rect, p)
        p.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, 0f, 0f, p)

        return bm
    }

}