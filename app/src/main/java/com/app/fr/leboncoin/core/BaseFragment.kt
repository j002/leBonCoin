package com.app.fr.leboncoin.core

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.fr.leboncoin.core.BaseActivity

abstract class BaseFragment : Fragment() {

    lateinit var activity: BaseActivity
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

}