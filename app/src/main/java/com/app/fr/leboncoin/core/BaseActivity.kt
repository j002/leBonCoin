package com.app.fr.leboncoin.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrognito.flashbar.Flashbar
import com.app.fr.leboncoin.ApiErrorListener
import com.app.fr.leboncoin.App
import com.app.fr.leboncoin.helpers.FlashbarHelper
import com.app.fr.leboncoin.ui.dialog.ProgressDialogFragment
import com.app.fr.leboncoin.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

abstract class BaseActivity : AppCompatActivity() {


   private lateinit var progressDialog: ProgressDialogFragment

    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as App
    }




    private fun createProgressDialog() {
        progressDialog = ProgressDialogFragment.createDialog()
    }





    fun showProgressDialog() {
        supportFragmentManager.executePendingTransactions()
        if (!progressDialog.isAdded) {
            progressDialog.show(this, "ProgressDialog")
        }
    }

    fun hideProgressDialog() {
        if (progressDialog.isAdded) {
            progressDialog.dismiss()
        }
    }




    fun showErrorFlashBar(message: String) {
        FlashbarHelper
            .createFlashBarError(this, getString(R.string.flashbar_becarful_no_network), message)
            .show()
    }

    private fun showFlashBarWithDelay() {
      /* if (!flashBarNoNetwork.isShown() && this !is SplashScreenActivity) {
            Handler().postDelayed({
                flashBarNoNetwork.show()
            }, 250)
        }*/
    }
}