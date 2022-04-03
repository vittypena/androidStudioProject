package es.ua.eps.componentesviewpersonalizados

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Grafica @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
)
    : View(context, attrs, defStyle){
    private val DEFAULT_SIZE = 100
    private var mPercentage = 0

    private var mPaintRed = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        color = Color.RED
    }

    private var mPaintBlue = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        color = Color.BLUE
    }

    private var mRectOval = RectF()

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.Grafica)
            mPercentage = ta.getInt(R.styleable.Grafica_percentage, 0)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val sweepAngle = mPercentage.toFloat() * 360f / 100f
        canvas.drawArc(mRectOval, 0f, sweepAngle, true, mPaintRed)
        canvas.drawArc(mRectOval, sweepAngle, 360 - sweepAngle, true, mPaintBlue)
    }

    fun setPercentage(value: Int) {
        mPercentage = value
        invalidate()
    }

    @SuppressLint("SwitchIntDef")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var width = DEFAULT_SIZE
        var height = DEFAULT_SIZE

        when (widthMode) {
            MeasureSpec.EXACTLY -> width = widthSize
            MeasureSpec.AT_MOST -> if (width > widthSize) width = widthSize
        }
        height = width // Forzamos a que el área sea cuadrada

        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = heightSize
                width = heightSize // Forzamos a que el área sea cuadrada
            }
            MeasureSpec.AT_MOST -> if (height > heightSize) {
                height = heightSize
                width = heightSize // Forzamos a que el área sea cuadrada
            }
        }

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (Math.min(width, height) / 2).toFloat()

        mRectOval.top = centerY - radius
        mRectOval.bottom = centerY + radius
        mRectOval.left = centerX - radius
        mRectOval.right = centerX + radius

        setMeasuredDimension(width, height)
    }
}