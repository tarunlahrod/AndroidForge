package com.tarunlahrod.androidforge.ui.atom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.tarunlahrod.androidforge.R

class AppIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.AppIconView
        )
        try {
            if (typedArray.hasValue(R.styleable.AppIconView_iconTint)) {
                imageTintList = typedArray.getColorStateList(
                    R.styleable.AppIconView_iconTint
                )
            }
        } finally {
            typedArray.recycle()
        }
    }
}