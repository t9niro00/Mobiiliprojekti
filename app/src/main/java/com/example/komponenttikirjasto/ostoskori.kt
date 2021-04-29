package com.example.komponenttikirjasto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.elecstore.RealtimeDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.ostoskori.*

//Ostoskorissa tulee näkyä valittu tuote, esim. LUL kohdassa textViewtuotenimi, hinta kohdassa TextViewtuotehinta
//ja tuotteiden yhteishinta kohdassa textView5. Osta- painikkeesta tulee toasti, että tuote ostettu.
class ostoskori : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().getReference("Komponentit")
    var database2 = FirebaseDatabase.getInstance().getReference("Historia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ostoskori)
        addBasket()

        button.setOnClickListener {
            Toast.makeText(applicationContext,"Tuote ostettu", Toast.LENGTH_LONG).show()
        }
    }

    fun addBasket() {

        val prodname1: TextView = findViewById(R.id.textViewTuotenimiKO1)
        val prodprice1: TextView = findViewById(R.id.textViewHintaKO1)

        Log.v("Höhöö", "jokumuuttuja")

        val query = database2.child("0").child("Komponenttihistoria").get()

        var jokumuuttuja = ""

        Log.v("Höhöö", jokumuuttuja)

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

        Thread.sleep(250)

        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("ShowToast")
            override fun onCancelled(p0: DatabaseError) {
                Log.v("Errori tuli:", "Errori")
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
                Log.v("Höhöööööö", jokumuuttuja)
            }

        })

    }
}