package com.example.komponenttikirjasto

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.elecstore.RealtimeDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.ostoskori.*
import java.lang.Error

class ostoskori2 : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().getReference("Mikrokontrollerit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ostoskori)
        Log.v("Höhöö", "jokumuuttuja")

        addBasket()

        button.setOnClickListener {
            Toast.makeText(applicationContext,"Tuote ostettu", Toast.LENGTH_LONG).show()
        }
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


        Log.v("Höhöö", "5")

        Thread.sleep(250)


        query.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful){
            Log.v("Höhöö", "perspillu")
            jokumuuttuja = task.getResult()?.value.toString()
            Log.v("jokumuuttuja: Val: ", jokumuuttuja)
        }
        else{
            Log.v("höhöö", "Timantit on ikuisia")
        }
        })

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
            }

        })
    }

}