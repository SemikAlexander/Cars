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

const val DEFAULT_DELAY_MS = 750L

inline fun View.onClickDebounce(delayMs: Long = DEFAULT_DELAY_MS, crossinline l: (View?) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var notClicked = true
        override fun onClick(view: View) {
            if (notClicked) {
                notClicked = false
                l(view)
                view.postDelayed({ notClicked = true }, delayMs)
            }
        }
    })
}