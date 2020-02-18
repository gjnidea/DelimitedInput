package gj.delimitedinput.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import gj.delimitedinput.R
import gj.delimitedinput.watcher.CurrencyInputWatcher
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class CurrencyInputEditText@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.editTextStyle
): TextInputEditText(context, attrs, defStyleAttr) {

    private val watcher: CurrencyInputWatcher
    private val editText = this
    private val minDecimalPlace: Int
    private val maxDecimalPlace: Int
    private val applyDecimal: Boolean

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.Currency, 0, 0)
            .apply {
                try {
                    minDecimalPlace = getInt(R.styleable.Currency_min_decimal_place, 2)
                    maxDecimalPlace = getInt(R.styleable.Currency_max_decimal_place, 2)
                    applyDecimal = getBoolean(R.styleable.Currency_apply_decimal, true)
                } finally {
                    recycle()
                }

                inputType = (InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
                watcher = CurrencyInputWatcher(editText)

                addTextChangedListener(watcher)
            }

    }

    fun getDefaultWatcher(): CurrencyInputWatcher {
        return watcher
    }

    fun getBigDecimal(): BigDecimal {
        var input = filteredInput()
        if (input.isEmpty()) {
            input = "0"
        }

        if (applyDecimal) {
            input = applyDecimal(input)
        }

        return input.toBigDecimal()
    }

    fun getStringInput(): String {
        var input = filteredInput()

        if (applyDecimal) {
            input = applyDecimal(input)
        }

        return input
    }

    private fun filteredInput(): String {
        var input = text.toString().replace(",", "").replace(" ", "")

        // user might input "3." then submit it to some api
        if (input.indexOf(".") == input.length) {
            input = input.replace(".", "")
        }

        return input
    }

    private fun applyDecimal(input: String): String {
        if (input.isEmpty()) return input

        val numberFormat = NumberFormat.getInstance(Locale.US)
        numberFormat.minimumFractionDigits = minDecimalPlace
        numberFormat.maximumFractionDigits = maxDecimalPlace

        return numberFormat.format(input.toBigDecimal()).replace(",", "")
    }

}