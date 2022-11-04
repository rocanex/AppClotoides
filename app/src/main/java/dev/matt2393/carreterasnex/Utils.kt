package dev.matt2393.carreterasnex

import android.os.Build
import android.text.Html


fun htmlToText(html: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
