package com.app.fr.leboncoin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.app.fr.leboncoin.R
import com.app.fr.leboncoin.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        openHomeActivity()

    }

    private fun openHomeActivity(){
        val handler = Handler()


        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
            }
            handler.post {
                progress_bar.visibility = View.INVISIBLE
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }
        }).start()
    }

}
