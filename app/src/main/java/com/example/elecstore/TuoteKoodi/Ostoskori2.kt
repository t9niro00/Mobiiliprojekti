package com.example.elecstore

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.elecstore.DatabaseKoodi.RealtimeDatabase
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.ostoskori.*

class ostoskori2 : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().getReference("Mikrokontrollerit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ostoskori)
        Log.v("Höhöö", "jokumuuttuja")
        addBasket()

        val actionBar = supportActionBar

        actionBar!!.title = "Ostoskori"

        actionBar.setDisplayHomeAsUpEnabled(true)

        button.setOnClickListener {
            Toast.makeText(applicationContext,"Tuote ostettu", Toast.LENGTH_LONG).show()
        }
        jatkaShoppailua.setOnClickListener {
           this.finish() //TODO sammuttaa vain sen ostoskori aktiviteetin, pitää testata muistaako tuotteita
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun addBasket(){



        Log.v("Höhöö", "1")
        val prodname1: TextView = findViewById(R.id.textViewTuotenimiKO1)
        Log.v("Höhöö", "2")
        val prodprice1: TextView = findViewById(R.id.textViewHintaKO1)
        var jokumuuttuja = ""
        Log.v("Höhöö", "3")
        val query = database2.child("1").child("Mikrokontrollerihistoria").get()
        Log.v("Höhöö", "4")
        val sum: TextView = findViewById(R.id.textView5)

        val rentprod1: TextView = findViewById(R.id.textViewTuotenimiMC1)
        val rentprice1: TextView = findViewById(R.id.textViewHintaMC1)

        rentprod1.text = "Vuokratuote"
        rentprice1.text = 10.0.toString() + "€"


        Log.v("Höhöö", "5")

        Thread.sleep(500)


        query.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.v("Höhöö", "perspillu")
                jokumuuttuja = task.getResult()?.value.toString()
                Log.v("jokumuuttuja: Val: ", jokumuuttuja)
            } else {
                Log.v("höhöö", "Timantit on ikuisia")
            }
        }

        Log.v("Höhöö", "6")

        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Log.v("Errori tuli.", "Errori")
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
                Log.v("Kalle on hanavesigoblin", realtimeDatabase.toString())
                Log.v("jokumuuttuja: Val: ", jokumuuttuja)


                if(jokumuuttuja == "0") {
                    val prodimg1 = Firebase.storage.reference.child("Images/pi4.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)


                        Glide.with(this@ostoskori2)
                                .load(imageUrl)
                                .into(imageView)

                    }
                }
                if(jokumuuttuja == "1") {
                    val prodimg1 = Firebase.storage.reference.child("Images/arduinouno.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)


                        Glide.with(this@ostoskori2)
                                .load(imageUrl)
                                .into(imageView)

                    }

                }
                if(jokumuuttuja == "2")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/edison.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)

                        Glide.with(this@ostoskori2)
                                .load(imageUrl)
                                .into(imageView)
                    }
                }
                if(jokumuuttuja == "3")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/arduinopromini.png")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)

                        Glide.with(this@ostoskori2)
                                .load(imageUrl)
                                .into(imageView)
                    }
                }
                if(jokumuuttuja == "4")
                {
                    val prodimg1 = Firebase.storage.reference.child("Images/jetson.webp")

                    prodimg1.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()
                        val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)

                        Glide.with(this@ostoskori2)
                                .load(imageUrl)
                                .into(imageView)
                    }
                }





            }

        })
    }

}