package jp.ogiwara.test.lobitest.other.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import jp.ogiwara.test.lobitest.R

class AspectRatioImageView : ImageView{

    companion object{
        const val DEFAULT_ASPECT_RATIO = 1
    }

    var widthRatio = 0
    var heightRatio = 0

    constructor(context: Context?): super(context){
        init(null,0,0)
    }

    constructor(context: Context?,
                attrs: AttributeSet?): super(context,attrs){
        init(attrs,0,0)
    }

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int): super(context,attrs,defStyleAttr){
        init(attrs,defStyleAttr,0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes){
        init(attrs,defStyleAttr,defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int){

        if(attrs == null){
            return
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView, defStyleAttr, defStyleRes)

        widthRatio = typedArray.getInteger(R.styleable.AspectRatioImageView_widthRatio, DEFAULT_ASPECT_RATIO)
        heightRatio = typedArray.getInteger(R.styleable.AspectRatioImageView_heightRatio, DEFAULT_ASPECT_RATIO)

        typedArray.recycle()

        validateRatio(widthRatio)
        validateRatio(heightRatio)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val sizePerRatio = width.toFloat() / widthRatio.toFloat()
        val height = Math.round(sizePerRatio * heightRatio)

        setMeasuredDimension(width,height)
    }

    private fun validateRatio(ratio: Int){
        if(ratio <= 0){
            throw IllegalArgumentException("ratio > 0")
        }
    }

}
