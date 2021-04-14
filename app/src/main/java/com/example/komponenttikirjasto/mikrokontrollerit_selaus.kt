package com.example.komponenttikirjasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class mikrokontrollerit_selaus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mikrokontrollerit_selaus)

        val actionBar = supportActionBar

        actionBar!!.title = "Mikrokontrollerit"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}
//takaisin koti aktivitettiin (activity_main)