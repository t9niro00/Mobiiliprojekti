package com.example.komponenttikirjasto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.elecstore.RealtimeDatabase
import com.example.elecstore.getData
import com.example.elecstore.getKompoData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

// Asetetaan data
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asetetaan databasen oksien nimet.

        var name1 = "Komponentit"
        var name2 = "Mikrokontrollerit"

        //Määritellään polku, josta lähdetään liikkeelle databasessa

        var database = FirebaseDatabase.getInstance().getReference("Products")

        //Asetetaan databaseen halutut arvot koodissa.

        database.child(name1).setValue(RealtimeDatabase("OMEGA", 1, 2))
        database.child(name2).setValue(RealtimeDatabase("LUL", 3, 4))

        //Asetetaan listeneri mikropiirien valintapainikkeeseen,
        //jota painaessa suoritetaan getData-luokan sisällä oleva koodi

        buttonMikropiirit.setOnClickListener {
            startActivity(Intent(applicationContext,getData::class.java))
        }

        buttonKomponentit.setOnClickListener {
            startActivity(Intent(applicationContext, getKompoData::class.java))
        }
        textViewTitle.setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }

        checkTheme()
    }

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

    private val preferences = getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()
}
//teema haku