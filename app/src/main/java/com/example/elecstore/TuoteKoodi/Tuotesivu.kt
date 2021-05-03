package com.example.elecstore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.MainActivity
import com.example.komponenttikirjasto.R
import kotlinx.android.synthetic.main.ostoskori.*

class tuotesivu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tuotesivu)


        val actionBar = supportActionBar

        actionBar!!.title = "Tuotesivu"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}
