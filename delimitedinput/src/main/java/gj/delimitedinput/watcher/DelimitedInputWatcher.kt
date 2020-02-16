package gj.delimitedinput.watcher

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.max

class DelimitedInputWatcher: TextWatcher {

    private val editText: TextInputEditText
    private val delimiter: String
    private val memberCount: Int

    constructor(editText: TextInputEditText, delimiter: String, memberCount: Int) {
        this.editText = editText
        this.delimiter = delimiter
        this.memberCount = memberCount
    }


    override fun afterTextChanged(s: Editable?) {
        if (s.isNullOrEmpty() || memberCount <= 0) return

        val prevLen = s.length
        val currentCursorPosition = editText.selectionEnd

        val filteredInput = s.toString().replace(delimiter, "")
        val splitString = filteredInput.chunked(memberCount)

        editText.removeTextChangedListener(this)
        editText.setText(splitString.joinToString(delimiter))
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