package com.example.elecstore.DatabaseKoodi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.elecstore.TuoteKoodi.tuoteGet2
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.komponentit_selaus.*
import kotlinx.android.synthetic.main.ostoskori.buttonTuoteKO1


class getData : AppCompatActivity() {
    //Määritellään databasen referenssi täälläkin

    var database = FirebaseDatabase.getInstance().getReference("Mikrokontrollerit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)
        getData()

        //Jokaiselle buttonille oma listeneri clickeille, jotta saadaan asetettua oikea
        //arvo mikrokontrollerihistoriaan databaseen.

        buttonTuoteKO1.setOnClickListener{
            database2.child("1").child("Mikrokontrollerihistoria").setValue("0")
            startActivity(Intent(applicationContext, tuoteGet2::class.java))
        }

        buttonTuoteKO2.setOnClickListener {
            database2.child("1").child("Mikrokontrollerihistoria").setValue("1")
            startActivity(Intent(applicationContext, tuoteGet2::class.java))
        }

        buttonTuoteKO3.setOnClickListener {
            database2.child("1").child("Mikrokontrollerihistoria").setValue("2")
            startActivity(Intent(applicationContext, tuoteGet2::class.java))
        }

        buttonTuoteKO4.setOnClickListener {
            database2.child("1").child("Mikrokontrollerihistoria").setValue("3")
            startActivity(Intent(applicationContext, tuoteGet2::class.java))
        }
        buttonTuoteKO5.setOnClickListener {
            database2.child("1").child("Mikrokontrollerihistoria").setValue("4")
            startActivity(Intent(applicationContext, tuoteGet2::class.java))
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

        val prodname1: TextView = findViewById(R.id.textViewTuotenimiKO1)
        val prodprice1: TextView = findViewById(R.id.textViewHintaKO1)
      //  val prodimg1: ImageView = findViewById(R.id.imageViewTuotekuvaKO1)
        val prodname2: TextView = findViewById(R.id.textViewTuotenimiKO2)
        val prodprice2: TextView = findViewById(R.id.textViewHintaKO2)
        //val prodimg2 : ImageView = findViewById(R.id.imageViewTuotekuvaKO2)
        val prodname3: TextView = findViewById(R.id.textViewTuotenimiKO3)
        val prodprice3: TextView = findViewById(R.id.textViewHintaKO3)
        //val prodimg3: ImageView = findViewById(R.id.imageViewTuotekuvaKO3)
        val prodname4: TextView = findViewById(R.id.textViewTuotenimiKO4)
        val prodprice4: TextView = findViewById(R.id.textViewHintaKO4)
        val prodname5: TextView = findViewById(R.id.textViewTuotenimiKO5)
        val prodprice5: TextView = findViewById(R.id.textViewHintaKO5)

        //Annetaan määritellyille muuttujille slotit, joihin liittää databasesta saatu tieto


        Firebase.storage.reference

        //Luodaan referenssi
        val raspberryRef = Firebase.storage.reference.child("Images/pi4.png")
        val arduinoRef = Firebase.storage.reference.child("Images/arduinouno.png")
        val edisonRef = Firebase.storage.reference.child("Images/edison.png")
        val arduinominiRef = Firebase.storage.reference.child("Images/arduinopromini.png")
        val jetsonRef = Firebase.storage.reference.child("Images/jetson.webp")




        //Haetaan kuva
        raspberryRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO1)

            //Ladataan kuva imageviewiin
            Glide.with(this)
                    .load(imageURL)
                    .into(imageView)

        }

        arduinoRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO2)

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView)
        }
        edisonRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO3)

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView)
        }
        arduinominiRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO4)

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView)
        }
        jetsonRef.downloadUrl.addOnSuccessListener { Uri ->
            val imageUrl = Uri.toString()
            val imageView = findViewById<ImageView>(R.id.imageViewTuotekuvaKO5)

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView)
        }





            database.addValueEventListener(object : ValueEventListener {
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
                    prodname4.text = realtimeDatabase4?.prodname
                    prodprice4.text = realtimeDatabase4?.prodprice.toString() + "€"
                    prodname5.text = realtimeDatabase5?.prodname
                    prodprice5.text = realtimeDatabase5?.prodprice.toString() + "€"


                }

            })

        }
    }