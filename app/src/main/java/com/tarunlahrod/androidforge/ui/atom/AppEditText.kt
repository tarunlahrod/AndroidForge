package com.tarunlahrod.androidforge.ui.atom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.tarunlahrod.androidforge.R

class AppEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        setTextAppearance(R.style.TextAppearance_AndroidForge_Body)
    }
}