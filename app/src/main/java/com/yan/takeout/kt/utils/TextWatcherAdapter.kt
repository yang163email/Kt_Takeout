package com.yan.takeout.kt.utils

import android.text.Editable
import android.text.TextWatcher

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 15:49
 *  @description : TextWatcher适配器
 */
open class TextWatcherAdapter: TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}