package com.tarunlahrod.androidforge.ui.molecule

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import com.tarunlahrod.androidforge.R
import com.tarunlahrod.androidforge.databinding.ViewAppIconTextBinding
import androidx.core.view.isVisible

class AppIconTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding = ViewAppIconTextBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.AppIconTextView
        ).apply {
            try {
                setText(this)
                setTextAppearance(this)
                setTextColor(this)
                setTextSize(this)
                setGravity(this)
                textAlignment(this)
                setFontFamily(this)

                setStartIcon(this)
                setEndIcon(this)

                setStartIconTint(this)
                setEndIconTint(this)

                setStartIconSize(this)
                setEndIconSize(this)

                setIconTextSpacing(this)

            } finally {
                recycle()
            }
        }
    }

    private fun setText(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_text)) {
            binding.tvText.text = typedArray.getText(R.styleable.AppIconTextView_android_text)
        }
    }

    private fun setTextAppearance(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_textAppearance)) {
            binding.tvText.setTextAppearance(
                typedArray.getResourceId(
                    R.styleable.AppIconTextView_android_textAppearance,
                    0
                )
            )
        }
    }

    private fun setTextColor(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_textColor)) {
            typedArray.getColorStateList(R.styleable.AppIconTextView_android_textColor)?.let {
                binding.tvText.setTextColor(it)
            }
        }
    }

    private fun setTextSize(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_textSize)) {
            binding.tvText.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                typedArray.getDimension(
                    R.styleable.AppIconTextView_android_textSize,
                    0f
                )
            )
        }
    }

    private fun setGravity(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_gravity)) {
            binding.tvText.gravity = typedArray.getInt(
                R.styleable.AppIconTextView_android_gravity,
                binding.tvText.gravity
            )
        }
    }

    private fun textAlignment(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_textAlignment)) {
            binding.tvText.textAlignment = typedArray.getInt(
                R.styleable.AppIconTextView_android_textAlignment,
                binding.tvText.textAlignment
            )
        }
    }

    private fun setFontFamily(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_android_fontFamily)) {
            binding.tvText.typeface = ResourcesCompat.getFont(
                context,
                typedArray.getResourceId(
                    R.styleable.AppIconTextView_android_fontFamily,
                    0
                )
            )
        }
    }

    private fun setStartIcon(typedArray: TypedArray) {
        typedArray.getDrawable(R.styleable.AppIconTextView_startIcon)?.let {
            binding.ivStartIcon.setImageDrawable(it)
            binding.ivStartIcon.isVisible = true
        }
    }

    private fun setEndIcon(typedArray: TypedArray) {
        typedArray.getDrawable(R.styleable.AppIconTextView_endIcon)?.let {
            binding.ivEndIcon.setImageDrawable(it)
            binding.ivEndIcon.isVisible = true
        }
    }

    private fun setStartIconTint(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_startIconTint)) {
            binding.ivStartIcon.imageTintList = typedArray.getColorStateList(
                R.styleable.AppIconTextView_startIconTint
            )
        }
    }

    private fun setEndIconTint(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_endIconTint)) {
            binding.ivEndIcon.imageTintList = typedArray.getColorStateList(
                R.styleable.AppIconTextView_endIconTint
            )
        }
    }

    private fun setStartIconSize(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_startIconSize)) {
            setIconSize(
                view = binding.ivStartIcon,
                size = typedArray.getDimensionPixelSize(R.styleable.AppIconTextView_startIconSize, 0)
            )
        }
    }

    private fun setEndIconSize(typedArray: TypedArray) {
        if (typedArray.hasValue(R.styleable.AppIconTextView_endIconSize)) {
            setIconSize(
                view = binding.ivEndIcon,
                size = typedArray.getDimensionPixelSize(R.styleable.AppIconTextView_endIconSize, 0)
            )
        }
    }

    private fun setIconSize(view: View, size: Int) {
        view.layoutParams = view.layoutParams.apply {
            width = size
            height = size
        }
    }

    private fun setIconTextSpacing(typedArray: TypedArray) {
        if (!typedArray.hasValue(R.styleable.AppIconTextView_iconTextSpacing)) {
            return
        }
        val spacing = typedArray.getDimensionPixelSize(
            R.styleable.AppIconTextView_iconTextSpacing,
            0
        )
        (binding.tvText.layoutParams as LayoutParams).apply {
            marginStart = if (binding.ivStartIcon.isVisible) {
                spacing
            } else {
                0
            }

            marginEnd = if (binding.ivEndIcon.isVisible) {
                spacing
            } else {
                0
            }
        }.also {
            binding.tvText.layoutParams = it
        }
    }
}