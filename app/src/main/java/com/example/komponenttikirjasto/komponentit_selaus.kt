package com.example.komponenttikirjasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class komponentit_selaus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)

        val actionBar = supportActionBar

        actionBar!!.title = "Komponentit"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}
//takaisin koti aktivitettiin (activity_main)