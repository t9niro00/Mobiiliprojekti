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

class getKompoData : AppCompatActivity(){
    //Määritellään databasen referenssi täälläkin

    var database = FirebaseDatabase.getInstance().getReference("Products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)
        getData()

        buttonTuoteKO1.setOnClickListener{
            startActivity(Intent(applicationContext,tuoteGet::class.java))
        }

        val actionBar = supportActionBar

        actionBar!!.title = "Komponentit"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
    //Luodaan funktio, joka hakee dataa.

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getData(){
        //Määritellään muuttujat

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto

        val prodname1: TextView = findViewById(R.id.textViewTuotenimiKO1)
        val prodprice1: TextView = findViewById(R.id.textViewHintaKO1)
        val prodname2: TextView = findViewById(R.id.textViewTuotenimiKO2)
        val prodprice2: TextView = findViewById(R.id.textViewHintaKO2)
        val prodname3: TextView = findViewById(R.id.textViewTuotenimiKO3)
        val prodprice3: TextView = findViewById(R.id.textViewHintaKO3)




        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data.

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                val realtimeDatabase = p0.child("Komponentit").child("0").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase2 = p0.child("Komponentit").child("1").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase3 = p0.child("Komponentit").child("2").getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"
                prodname2.text = realtimeDatabase2?.prodname
                prodprice2.text = realtimeDatabase2?.prodprice.toString() + "€"
                prodname3.text = realtimeDatabase3?.prodname
                prodprice3.text = realtimeDatabase3?.prodprice.toString() + "€"
            }

        })
    }
}