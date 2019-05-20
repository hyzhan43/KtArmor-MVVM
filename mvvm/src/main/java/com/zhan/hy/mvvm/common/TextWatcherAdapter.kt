package com.zhan.hy.mvvm.common

import android.text.Editable
import android.text.TextWatcher

/**
 * @author  hyzhan
 * @date    2019/5/20
 * @desc    TODO
 */
class TextWatcherAdapter : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}