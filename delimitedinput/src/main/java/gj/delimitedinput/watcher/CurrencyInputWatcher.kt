package gj.delimitedinput.watcher

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import kotlin.math.max

class CurrencyInputWatcher: TextWatcher {

    private val editText: TextInputEditText

    constructor(editText: TextInputEditText) {
        this.editText = editText
    }


    override fun afterTextChanged(s: Editable?) {
        if (s?.trim().isNullOrEmpty()) return

        val prevLen = s!!.length
        val currentCursorPosition = editText.selectionEnd

        // reattach fraction part after adding comma.
        // not sure how to include decimals in numberFormat
        val filteredInput = s.toString().replace(",", "")
        val fractionInd = filteredInput.indexOf(".")

        var wholeNumber = filteredInput
        var fraction = ""

        if (fractionInd != -1) {
            wholeNumber = filteredInput.substring(0, fractionInd)
            fraction = filteredInput.substring(fractionInd, filteredInput.length)
        }

        val formatter = NumberFormat.getInstance(Locale.US)

        editText.removeTextChangedListener(this)
        editText.setText(String.format("%s%s", formatter.format(BigDecimal(wholeNumber)), fraction))
        editText.addTextChangedListener(this)

        editText.text?.let {
            when {
                editText.text!!.length > prevLen -> {
                    editText.setSelection(currentCursorPosition + 1)
                }
                editText.text!!.length < prevLen -> {
                    editText.setSelection(max(0, currentCursorPosition - 1))
                }
                else -> {
                    editText.setSelection(currentCursorPosition)
                }
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}