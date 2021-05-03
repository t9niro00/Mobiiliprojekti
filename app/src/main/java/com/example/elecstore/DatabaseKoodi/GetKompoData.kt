package com.example.elecstore.DatabaseKoodi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.elecstore.TuoteKoodi.tuoteGet
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.komponentit_selaus.*
import kotlinx.android.synthetic.main.ostoskori.buttonTuoteKO1

class getKompoData : AppCompatActivity(){
    //Määritellään databasen referenssi täälläkin

    var database = FirebaseDatabase.getInstance().getReference("Historia")
    var database2 = FirebaseDatabase.getInstance().getReference("Komponentit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)
        getData()

        buttonTuoteKO1.setOnClickListener{
            database.child("0").child("Komponenttihistoria").setValue("0")
            startActivity(Intent(applicationContext, tuoteGet::class.java))
        }

        buttonTuoteKO2.setOnClickListener {
            database.child("0").child("Komponenttihistoria").setValue("1")
            startActivity(Intent(applicationContext, tuoteGet::class.java))
        }

        buttonTuoteKO3.setOnClickListener {
            database.child("0").child("Komponenttihistoria").setValue("2")
            startActivity(Intent(applicationContext, tuoteGet::class.java))
        }

        buttonTuoteKO4.setOnClickListener {
            database.child("0").child("Komponenttihistoria").setValue("3")
            startActivity(Intent(applicationContext, tuoteGet::class.java))
        }
        buttonTuoteKO5.setOnClickListener {
            database.child("0").child("Komponenttihistoria").setValue("4")
            startActivity(Intent(applicationContext, tuoteGet::class.java))
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
        val prodname4: TextView = findViewById(R.id.textViewTuotenimiKO4)
        val prodprice4: TextView = findViewById(R.id.textViewHintaKO4)
        val prodname5: TextView = findViewById(R.id.textViewTuotenimiKO5)
        val prodprice5: TextView = findViewById(R.id.textViewHintaKO5)


        Firebase.storage.reference

        //Luodaan referenssi
        val ledRef = Firebase.storage.reference.child("Images/Led.png")
        val wireRef = Firebase.storage.reference.child("Images/wires.png")
        val resistorRef = Firebase.storage.reference.child("Images/resistor.png")
        val capacitorRef = Firebase.storage.reference.child("Images/capacitor.png")
        val switchesRef = Firebase.storage.reference.child("Images/switches.webp")

        //Haetaan kuva
        ledRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)

            //Ladataan kuva imageviewiin
            Glide.with(this)
                .load(imageURL)
                .into(imageView)

        }

        wireRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO2)

            Glide.with(this)
                .load(imageUrl)
                .into(imageView)

        }
        resistorRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO3)

            Glide.with(this)
                .load(imageUrl)
                .into(imageView)

        }
        capacitorRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO4)

            Glide.with(this)
                .load(imageUrl)
                .into(imageView)
        }

        switchesRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO5)

            Glide.with(this)
                .load(imageUrl)
                .into(imageView)
        }




        database2.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                //Toast.makeText(this@MainActivity, p0.code, Toast.LENGTH_SHORT)
            }

            //Määritellään mistä puusta haetaan data.

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                val realtimeDatabase = p0.child("0").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase2 = p0.child("1").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase3 = p0.child("2").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase4 = p0.child("3").getValue(RealtimeDatabase::class.java)
                val realtimeDatabase5 = p0.child("4").getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"
                prodname2.text = realtimeDatabase2?.prodname
                prodprice2.text = realtimeDatabase2?.prodprice.toString() + "€"
                prodname3.text = realtimeDatabase3?.prodname
                prodprice3.text = realtimeDatabase3?.prodprice.toString() + "€"
                prodname4.text = realtimeDatabase3?.prodname
                prodprice4.text = realtimeDatabase3?.prodprice.toString() + "€"
                prodname5.text = realtimeDatabase3?.prodname
                prodprice5.text = realtimeDatabase3?.prodprice.toString() + "€"
            }

        })
    }
}