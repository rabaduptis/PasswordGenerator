package com.root14.passwordgenerator.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.root14.passwordgenerator.view.state.Result

class ClipBoardUtil(context: Context) {

    private var clipboard: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun copy(data: String): Result<Boolean> {
        return try {
            val clip = ClipData.newPlainText("PasswordGenerator", data)
            clipboard.setPrimaryClip(clip)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    }
}