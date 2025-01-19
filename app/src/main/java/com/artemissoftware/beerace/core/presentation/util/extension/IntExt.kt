package com.artemissoftware.beerace.core.presentation.util.extension

import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.presentation.util.UiText

fun Int.toOrdinal(): UiText {
    return when (this) {
        1 -> UiText.StringResource(R.string._1st)
        2 -> UiText.StringResource(R.string._2nd)
        3 -> UiText.StringResource(R.string._3rd)
        else -> UiText.StringResource(R.string.th, this)
    }
}

fun Int.toMedal():Int {
    return when (this) {
        1 -> R.drawable.ic_medal_gold
        2 -> R.drawable.ic_medal_silver
        3 -> R.drawable.ic_medal_bronze
        else -> 0
    }
}