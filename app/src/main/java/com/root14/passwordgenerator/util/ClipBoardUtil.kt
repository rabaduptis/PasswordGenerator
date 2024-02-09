package com.root14.passwordgenerator.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipBoardUtil(context: Context) {

    private lateinit var clipboard: ClipboardManager

    fun clipBoardUtil(context: Context) {
        clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    }

    fun copy(data: String) {
        val clip = ClipData.newPlainText("PasswordGenerator", data)
        clipboard.setPrimaryClip(clip)
    }
}