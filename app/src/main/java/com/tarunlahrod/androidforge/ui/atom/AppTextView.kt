package com.tarunlahrod.androidforge.ui.atom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.tarunlahrod.androidforge.R

class AppTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        setTextAppearance(R.style.TextAppearance_AndroidForge_Body)
    }
}