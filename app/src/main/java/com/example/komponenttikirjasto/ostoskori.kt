package com.example.komponenttikirjasto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.elecstore.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.ostoskori.*

//Ostoskorissa tulee näkyä valittu tuote, esim. LUL kohdassa textViewtuotenimi, hinta kohdassa TextViewtuotehinta
//ja tuotteiden yhteishinta kohdassa textView5. Osta- painikkeesta tulee toasti, että tuote ostettu.
class ostoskori : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().getReference("Products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ostoskori)
        addBasket()

        button.setOnClickListener {
            Toast.makeText(applicationContext,"Tuote ostettu", Toast.LENGTH_LONG).show()
        }
    }

    fun addBasket(){

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

            //Määritellään mistä puusta haetaan data

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                val realtimeDatabase = p0.child("Mikrokontrollerit").getValue(RealtimeDatabase::class.java)
                prodname1.text = realtimeDatabase?.prodname
                prodprice1.text = realtimeDatabase?.prodprice.toString() + "€"



            }

        })

    }

}