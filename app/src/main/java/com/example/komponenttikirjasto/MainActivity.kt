package com.example.komponenttikirjasto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.komponenttikirjasto.R.id.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(buttonMikropiirit).setOnClickListener {
            val intent = Intent(this, mikrokontrollerit_selaus::class.java)
            startActivity(intent)
        }

        findViewById<Button>(buttonKomponentit).setOnClickListener {
            val intent = Intent(this, komponentit_selaus::class.java)
            startActivity(intent)
        }

        findViewById<Button>(textViewTitle).setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }

        checkTheme()
    }
// nappulat joilla siirtyä toisiin aktiviteetteihin

    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }
}
//kun app aukeaa tämä tarkistaa mikä teema viimeksi asetettuna ja automaattisesti ottaa tämän käyttöön

class MyPreferences(context: Context?) {

    companion object {
        private const val DARK_STATUS = "io.github.manuelernesto.DARK_STATUS"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()
}
//teema haku