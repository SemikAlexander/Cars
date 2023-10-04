package alexander.test.cars.ui.extentions

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun bitDepth(value: Int) =
    value.toString().reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
