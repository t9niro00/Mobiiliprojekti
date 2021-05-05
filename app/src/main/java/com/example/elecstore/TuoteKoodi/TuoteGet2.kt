package com.example.elecstore.TuoteKoodi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.elecstore.DatabaseKoodi.RealtimeDatabase
import com.example.elecstore.ostoskori2
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.tuotesivu.*

class tuoteGet2 : AppCompatActivity() {
    //Määritellään databasereferenssi

    var database = FirebaseDatabase.getInstance().getReference("Mikrokontrollerit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tuotesivu)
        getTuote()

        val actionBar = supportActionBar

        Firebase.storage.reference

        actionBar!!.title = "Tuote info"

        actionBar.setDisplayHomeAsUpEnabled(true)

        buttonLisaaOstoskoriin.setOnClickListener{
            startActivity(Intent(applicationContext, ostoskori2::class.java))
        }


}
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getTuote() {
        //Määritellään muuttujat

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto

        val prodname1: TextView = findViewById(R.id.textViewTuotenimi)
        val prodprice1: TextView = findViewById(R.id.textViewHinta)
        val prodval1: TextView = findViewById(R.id.textViewSaatavuus)


        val query = database2.child("1").child("Mikrokontrollerihistoria").get()

        var jokumuuttuja = ""

        query.addOnCompleteListener { task ->
            jokumuuttuja = task.result?.value.toString()
        }


        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                while (jokumuuttuja.isEmpty()) {
                    Toast.makeText(applicationContext, "Kalle on lyhyt", Toast.LENGTH_LONG)
                }
                val realtimeDatabase = p0.child(jokumuuttuja).getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"
                prodval1.text = realtimeDatabase?.prodval.toString() + " kpl"




                    if(jokumuuttuja == "0") {
                     val prodimg1 = Firebase.storage.reference.child("Images/pi4.png")

                     prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                         val imageUrl = Uri.toString()
                         val imageView = findViewById<ImageView>(R.id.imageViewTuotekuva54)


                         Glide.with(this@tuoteGet2)
                             .load(imageUrl)
                             .into(imageView)

                     }
                 }
                    if(jokumuuttuja == "1") {
                        val prodimg1 = Firebase.storage.reference.child("Images/arduinouno.png")

                        prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                            val imageUrl = Uri.toString()
                            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuva54)


                            Glide.with(this@tuoteGet2)
                                .load(imageUrl)
                                .into(imageView)

                        }

                    }
                if(jokumuuttuja == "2")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/edison.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuva54)

                        Glide.with(this@tuoteGet2)
                            .load(imageUrl)
                            .into(imageView)
                    }
                }
                if(jokumuuttuja == "3")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/arduinopromini.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuva54)

                        Glide.with(this@tuoteGet2)
                                .load(imageUrl)
                                .into(imageView)
                    }
                }
                if(jokumuuttuja == "4")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/jetson.webp")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuva54)

                        Glide.with(this@tuoteGet2)
                                .load(imageUrl)
                                .into(imageView)
                    }
                }

                Log.v("Kalle on hanavesigoblin", realtimeDatabase.toString())

            }

        })
    }
}