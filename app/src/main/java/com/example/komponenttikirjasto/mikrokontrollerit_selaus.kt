package com.example.komponenttikirjasto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.R.id.buttonTuoteMC1

class mikrokontrollerit_selaus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mikrokontrollerit_selaus)

        findViewById<Button>(buttonTuoteMC1).setOnClickListener {
            val intent = Intent(this, tuotesivu::class.java)
            startActivity(intent) // testi nappi, avaa tuotesivun
        }

        val actionBar = supportActionBar

        actionBar!!.title = "Mikrokontrollerit"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}
//takaisin koti aktivitettiin (activity_main)