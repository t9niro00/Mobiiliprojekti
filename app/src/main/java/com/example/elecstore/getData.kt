package com.example.elecstore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.ostoskori.*

class getData : AppCompatActivity() {
    //Määritellään databasen referenssi täälläkin

    var database = FirebaseDatabase.getInstance().getReference("Products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)
        getData()

        buttonTuoteKO1.setOnClickListener{
            startActivity(Intent(applicationContext,tuoteGet2::class.java))
        }

        val actionBar = supportActionBar

        actionBar!!.title = "Mikrokontrollerit"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    //Luodaan funktio, joka hakee dataa.

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getData(){
        //Määritellään muuttujat

        lateinit var prodname1: TextView
        lateinit var prodprice1: TextView

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto

        prodname1 = findViewById(R.id.textViewTuotenimiKO1)
        prodprice1 = findViewById(R.id.textViewHintaKO1)




        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data.

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                val realtimeDatabase = p0.child("Mikrokontrollerit").child("Tuotteet").getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"

            }

        })
    }
}