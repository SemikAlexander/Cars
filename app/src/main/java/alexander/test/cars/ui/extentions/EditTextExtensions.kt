package alexander.test.cars.ui.extentions

import android.annotation.SuppressLint
import android.util.Log
import android.widget.EditText

@SuppressLint("StaticFieldLeak")
private var lastChangeEditText: EditText? = null

fun CharSequence?.toLongNoDividers(): Long {
    var str = ""
    this.toString().forEach {
        if (it != ' ') {
            str += it
        }
    }
    return if (str != "") str.toLongOrNull() ?: 0 else 0
}

fun EditText.setTextAndCursorPosition(text: String, position: Int) {
    try {
        this.setText(text)
        if (position <= this.length()) {
            this.setSelection(position)
        } else {
            this.setSelection(this.length())
        }
    } catch (e: Throwable) {
        Log.e("teingExeption", "setTextAndCursorPosition", e)
    }
}

fun String.addDividers(): String {
    return reversed()
        .toList()
        .chunked(3)
        .joinToString(" ") { it.joinToString("") }
        .reversed()
}

fun setMask(editText: EditText) {
    val cursorPosition = editText.selectionStart
    val spaceCount = editText.text.count { it == ' ' }
    if (lastChangeEditText != editText) {
        val defaultText = editText.text.toString()
        val changedText = editText.text.toLongNoDividers().toString().trim().addDividers()
        val changedSpaceCount = changedText.count { it == ' ' }
        if (defaultText != changedText) {
            lastChangeEditText = editText
            editText.setTextAndCursorPosition(
                changedText,
                cursorPosition + when {
                    spaceCount < changedSpaceCount -> {
                        +1
                    }

                    spaceCount > changedSpaceCount -> {
                        -1
                    }

                    else -> {
                        0
                    }
                }
            )
        }
    } else {
        lastChangeEditText = null
    }
}