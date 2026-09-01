package com.tarunlahrod.androidforge.ui.atom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.tarunlahrod.androidforge.R

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var buttonStyle: Int = BUTTON_STYLE_PRIMARY

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.AppButton
        )
        try {
            buttonStyle = typedArray.getInt(
                R.styleable.AppButton_appButtonStyle,
                BUTTON_STYLE_PRIMARY
            )
        } finally {
            typedArray.recycle()
        }

        applyButtonStyle()
    }

    private fun applyButtonStyle() {
        when (buttonStyle) {
            BUTTON_STYLE_PRIMARY -> {
                setBackgroundResource(R.drawable.bg_button_primary)
            }

            BUTTON_STYLE_SECONDARY -> {
                setBackgroundResource(R.drawable.bg_button_secondary)
            }

            BUTTON_STYLE_DESTRUCTIVE -> {
                setBackgroundResource(R.drawable.bg_button_destructive)
            }
        }
    }

    companion object {
        private const val BUTTON_STYLE_PRIMARY = 0
        private const val BUTTON_STYLE_SECONDARY = 1
        private const val BUTTON_STYLE_DESTRUCTIVE = 2

    }
}