package gj.delimitedinput.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import gj.delimitedinput.watcher.CurrencyInputWatcher
import java.math.BigDecimal

class CurrencyInputEditText: TextInputEditText {

    private val watcher: CurrencyInputWatcher = CurrencyInputWatcher(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inputType = (InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
        addTextChangedListener(watcher)
    }

    fun getDefaultWatcher(): CurrencyInputWatcher {
        return watcher
    }

    fun getBigDecimal(): BigDecimal {
        var input = filteredInput()
        if (input.isEmpty()) {
            input = "0"
        }
        return input.toBigDecimal()
    }

    fun getStringInput(): String {
        return filteredInput()
    }

    private fun filteredInput(): String {
        var input = text.toString().replace(",", "")

        // user might input "3." then submit it to some api
        if (input.indexOf(".") == input.length) {
            input = input.replace(".", "")
        }

        return input.trim()
    }

}