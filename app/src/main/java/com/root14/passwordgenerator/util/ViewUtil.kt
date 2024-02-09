package com.root14.passwordgenerator.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.root14.passwordgenerator.R

class ViewUtil {
    companion object {

        /**
         * @param baseView view's base view object.
         * @sample View.findViewById(R.id.main)
         */
        fun setInset(baseView: View) {
            ViewCompat.setOnApplyWindowInsetsListener(baseView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

    }
}