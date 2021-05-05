package com.example.komponenttikirjasto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.elecstore.DatabaseKoodi.getData
import com.example.elecstore.DatabaseKoodi.getKompoData
import com.example.elecstore.settings
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*

// Asetetaan data
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Määritellään polku, josta lähdetään liikkeelle databasessa

        FirebaseDatabase.getInstance().getReference("Products")

        Firebase.storage.reference

        //Luodaan referenssi
        val mcuRef = Firebase.storage.reference.child("Images/raspberrypi.png")
        val compRef = Firebase.storage.reference.child("Images/komponentit.png")

        //Haetaan kuva
        mcuRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.buttonMikropiirit)

            //Ladataan kuva imageviewiin
            Glide.with(this)
                    .load(imageURL)
                    .into(imageView)

            checkTheme()
        }

        compRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.buttonKomponentit)

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView)

                checkTheme()
        }

        //Asetetaan databaseen halutut arvot koodissa.

        /*database.child(name1).setValue(RealtimeDatabase("2N1711 75V", 0.81, 1, 60))
        database.child(name1).setValue(RealtimeDatabase("2N3904 TO-92", 0.10, 2, 17))
        database.child(name1).setValue(RealtimeDatabase("LCD -screen LM1125 2x16", 6.0, 3, 5))
        database.child(name1).setValue(RealtimeDatabase("Resistor 1.00 ohm, 1% ... 2%, 0.4 - 0.6W",0.2, 4, 50))
        database.child(name1).setValue(RealtimeDatabase("EL1- AV102-12 Variable Attenuator 1.70-2.00 GHz",0.60, 5, 13))
        database.child(name2).setValue(RealtimeDatabase("Arduino Uno", 22.0, 1, 10))
        database.child(name2).setValue(RealtimeDatabase("NVIDIA Jetson Nano", 99.0, 2, 5))
        database.child(name2).setValue(RealtimeDatabase("Arduino Mega 2560", 39.0, 3, 5))
        database.child(name2).setValue(RealtimeDatabase("Raspberry Pi 3 A+", 25.0, 4, 5))
        database.child(name2).setValue(RealtimeDatabase("Arduino Pro Mini 328", 10.0, 5, 10))*/

        //Asetetaan listeneri mikropiirien valintapainikkeeseen,
        //jota painaessa suoritetaan getData-luokan sisällä oleva koodi

        //Toiminnallisuudet napeille joiden kautta päästään UI:ssa eteenpäin
        buttonMikropiirit.setOnClickListener {
            startActivity(Intent(applicationContext, getData::class.java))
        }

        buttonKomponentit.setOnClickListener {
            startActivity(Intent(applicationContext, getKompoData::class.java))
        }
        textViewTitle.setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }
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
//kun app aukeaa tämä tarkistaa mikä teema viimeksi asetettuna muistiin ja automaattisesti ottaa tämän käyttöön

class MyPreferences(context: Context?) {

    companion object {
        private const val DARK_STATUS = "io.github.manuelernesto.DARK_STATUS"
    }

    private val preferences = getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()
}
//Hakee tallennetut preferenssit teemaan ja tallentaa ne