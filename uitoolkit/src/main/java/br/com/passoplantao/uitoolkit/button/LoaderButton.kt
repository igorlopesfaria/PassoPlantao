package br.com.passoplantao.uitoolkit.button

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.text.SpannableString
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.passoplantao.uitoolkit.R


@SuppressLint("CustomViewStyleable")
class LoaderButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.uitoolkit_loaderButtonStyle
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar
    private val button: AppCompatButton
    private var text: String? = null

    init {
        inflate(context, R.layout.button_loader, this)

        progressBar = findViewById(R.id.progressBar)
        button = findViewById(R.id.button)

        val styledAttributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.uitoolkit_LoaderButton, defStyleAttr,
            R.style.UIToolkit_Widget_Button
        )
        initComponent(styledAttributes)

        styledAttributes.recycle()
    }

    private fun initComponent(styledAttributes: TypedArray) {
        text = styledAttributes.getString(R.styleable.uitoolkit_LoaderButton_uitoolkit_title)
        val style = getStyle(styledAttributes)
        applyStyle(style)
        isEnabled = styledAttributes.getBoolean(R.styleable.uitoolkit_LoaderButton_uitoolkit_enabled, true)
        setLoading(false)
    }

    private fun getStyle(styledAttributes: TypedArray): Style {
        val diamondStyle = styledAttributes.getInt(
            R.styleable.uitoolkit_LoaderButton_uitoolkit_style,
            Style.NORMAL.ordinal
        )
        return Style.values()[diamondStyle]
    }

    fun applyStyle(style: Style) = when (style) {
        Style.NORMAL -> {
        }
        Style.SECONDARY -> applySecondaryStyle()
    }

    private fun applySecondaryStyle() {
        button.background = ContextCompat.getDrawable(context, R.drawable.background_button_primary)
        button.setTextColor(ContextCompat.getColor(context, R.color.white))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            progressBar.indeterminateDrawable.colorFilter = BlendModeColorFilter(ContextCompat.getColor(context, R.color.white), BlendMode.SRC_ATOP)
        } else {
            progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun setState(state: State) {
        when(state){
            State.LOADING -> {
                setLoading(true)
                isEnabled = true
                isClickable = false
            }
            State.DISABLED -> {
                setLoading(false)
                isEnabled = false
                isClickable = false
            }
            State.NORMAL -> {
                setLoading(false)
                isEnabled = true
                isClickable = true
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            button.text = ""
            progressBar.visibility = View.VISIBLE
        } else {
            button.text = text
            progressBar.visibility = View.INVISIBLE
        }

        button.isClickable = !loading
    }

    fun setText(text: SpannableString) {
        this.text = text.toString()
        button.text = text
    }

    fun setText(text: CharSequence) {
        this.text = text.toString()
        button.text = text
    }

    fun getText() = this.text

    override fun setOnClickListener(listener: OnClickListener?) = button.setOnClickListener(listener)

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
    }

    enum class State {
        LOADING,
        NORMAL,
        DISABLED
    }

    enum class Style {
        NORMAL,
        SECONDARY
    }
}
