package `in`.book.repo.open

import android.view.View

fun View?.setViewGone() {
    if (this?.visibility != View.GONE) {
        this?.visibility = View.GONE
    }
}

fun View?.setViewVisible() {
    if (this?.visibility != View.VISIBLE) {
        this?.visibility = View.VISIBLE
    }
}

fun View?.setViewInvisible() {
    if (this?.visibility != View.INVISIBLE) {
        this?.visibility = View.INVISIBLE
    }
}