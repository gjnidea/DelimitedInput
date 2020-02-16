package gj.delimitedinput.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import gj.delimitedinput.R
import gj.delimitedinput.watcher.DelimitedInputWatcher

class DelimitedInputEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.editTextStyle
): TextInputEditText(context, attrs, defStyleAttr) {

    private val watcher: DelimitedInputWatcher
    private val delimiter: String
    private val memberCount: Int
    private val editText = this

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.Delimiter, 0, 0)
            .apply {
                try {

                    val declaredDelimiter = getString(R.styleable.Delimiter_delimiter)
                    memberCount = getInt(R.styleable.Delimiter_member_count, 0)
                    delimiter = if (declaredDelimiter.isNullOrEmpty()) {
                        ""
                    } else {
                        declaredDelimiter
                    }
                } finally {
                    recycle()
                }
                watcher = DelimitedInputWatcher(editText, delimiter, memberCount)
                addTextChangedListener(watcher)
        }
    }

    fun getInput(): String {
        return text.toString().replace(delimiter, "")
    }

}