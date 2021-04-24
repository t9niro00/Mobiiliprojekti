package com.example.elecstore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.R
import com.example.komponenttikirjasto.ostoskori
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.tuotesivu.*

class tuoteGet : AppCompatActivity() {
    //Määritellään databasereferenssi

    var database = FirebaseDatabase.getInstance().getReference("Products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tuotesivu)
        getTuote()

        val actionBar = supportActionBar

        actionBar!!.title = "Tuote info"

        actionBar.setDisplayHomeAsUpEnabled(true)

        buttonLisaaOstoskoriin.setOnClickListener {
            startActivity(Intent(applicationContext,ostoskori::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getTuote(){
        //Määritellään muuttujat

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto

        var prodname1: TextView = findViewById(R.id.textViewTuotenimi)
        var prodprice1: TextView = findViewById(R.id.textViewHinta)
        var prodval1: TextView = findViewById(R.id.textViewSaatavuus)


        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                val realtimeDatabase = p0.child("Komponentit").child("Tuotteet").getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"
                prodval1.text = realtimeDatabase?.prodval.toString() + " kpl"


            }

        })
    }
}
