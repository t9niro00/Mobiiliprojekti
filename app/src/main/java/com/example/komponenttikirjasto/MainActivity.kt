package com.example.komponenttikirjasto


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.elecstore.RealtimeDatabase
import com.example.elecstore.getData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.komponentit_selaus.*
import org.w3c.dom.Text
import java.lang.StringBuilder



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

        buttonMikropiirit.setOnClickListener{
            startActivity(Intent(applicationContext,getData::class.java))
        }

    }
}









