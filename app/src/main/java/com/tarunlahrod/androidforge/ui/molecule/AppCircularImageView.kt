package com.tarunlahrod.androidforge.ui.molecule

import android.content.Context
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.tarunlahrod.androidforge.R
import com.tarunlahrod.androidforge.ui.atom.AppImageView

class AppCircularImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppImageView(context, attrs, defStyleAttr) {

    private var borderWidth = 0f
    private var borderColor = 0

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    private val rect = RectF()

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.AppCircularImageView
        ).apply {
            try {
                borderWidth = getDimension(
                    R.styleable.AppCircularImageView_borderWidth,
                    0f
                )

                borderColor = getColor(
                    R.styleable.AppCircularImageView_borderColor,
                    0
                )

                borderPaint.strokeWidth = borderWidth
                borderPaint.color = borderColor
            } finally {
                recycle()
            }
        }

        clipToOutline = true
        outlineProvider = CircularOutlineProvider()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (borderWidth > 0f) {
            val halfBorder = borderWidth / 2f

            rect.set(
                halfBorder,
                halfBorder,
                width - halfBorder,
                height - halfBorder
            )

            canvas.drawOval(rect, borderPaint)
        }
    }

    private inner class CircularOutlineProvider : android.view.ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setOval(0, 0, view?.width ?: 0, view?.height ?: 0)
        }
    }
}