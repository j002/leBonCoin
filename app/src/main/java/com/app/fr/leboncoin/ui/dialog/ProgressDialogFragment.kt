/*
 * Copyright (c) 2018 by Appndigital, Inc.
 * All Rights Reserved
 */

package com.app.fr.leboncoin.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.app.fr.leboncoin.R
import com.app.fr.leboncoin.core.BaseActivity


class ProgressDialogFragment :androidx.fragment.app.DialogFragment() {
    lateinit var mContext: Context

    companion object {
        @JvmStatic
        fun createDialog(): ProgressDialogFragment {
            val frag = ProgressDialogFragment()
            return frag
        }
    }

    fun show(activity: BaseActivity, tag: String) {
        super.show(activity.supportFragmentManager, tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ProgressDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        return inflater.inflate(R.layout.dialog_progress, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
    }

}
