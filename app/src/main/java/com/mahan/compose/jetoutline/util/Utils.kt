package com.mahan.compose.jetoutline.util

import com.mahan.compose.jetoutline.R

fun getCorrectIconId(name: String): Int = when (name) {
    "Servers" -> R.drawable.ic_baseline_servers
    "Submit Feedback" -> R.drawable.ic_baseline_feedback
    "About" -> R.drawable.ic_baseline_info
    "Help" -> R.drawable.ic_baseline_help
    "Change Language" -> R.drawable.ic_baseline_language
    else -> -1
}