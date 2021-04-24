package com.example.elecstore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.R
import com.example.komponenttikirjasto.ostoskori
import com.example.komponenttikirjasto.ostoskori2
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.tuotesivu.*
import org.w3c.dom.Text

class tuoteGet2 : AppCompatActivity() {
    //Määritellään databasereferenssi

    var database = FirebaseDatabase.getInstance().getReference("Mikrokontrollerit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tuotesivu)
        getTuote()


        val actionBar = supportActionBar

        actionBar!!.title = "Tuote info"

        actionBar.setDisplayHomeAsUpEnabled(true)

        buttonLisaaOstoskoriin.setOnClickListener {
            startActivity(Intent(applicationContext, ostoskori2::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getTuote(){
        //Määritellään muuttujat

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto

        val prodname1: TextView = findViewById(R.id.textViewTuotenimi)
        val prodprice1: TextView = findViewById(R.id.textViewHinta)
        val prodval1: TextView = findViewById(R.id.textViewSaatavuus)

        val query = database2.child("1").child("Mikrokontrollerihistoria").get()

        var jokumuuttuja = ""

        query.addOnCompleteListener(OnCompleteListener { task ->
            jokumuuttuja = task.getResult()?.value.toString()
        })



        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                while (jokumuuttuja.length == 0) {
                    Toast.makeText(applicationContext, "Kalle on lyhyt", Toast.LENGTH_LONG)
                }
                val realtimeDatabase = p0.child(jokumuuttuja).getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"
                prodval1.text = realtimeDatabase?.prodval.toString() + " kpl"
                Log.v("Kalle on hanavesigoblin", realtimeDatabase.toString())

            }

        })
    }

}
