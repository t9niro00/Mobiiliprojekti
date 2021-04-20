package com.example.komponenttikirjasto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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