package com.example.komponenttikirjasto


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elecstore.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.komponentit_selaus.*
import java.lang.StringBuilder

// Asetetaan data
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.komponentit_selaus)

        var database = FirebaseDatabase.getInstance().getReference("Products")

        var name1 = "Komponentit"
        var name2 = "Mikrokontrollerit"


        database.child(name1).setValue(RealtimeDatabase("Infra", 1, 6))
        database.child(name2).setValue(RealtimeDatabase("Puna", 2, 7))
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }

    // Haetaan data

    var getData = object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            var sbname = StringBuilder()
            var sbprice = StringBuilder()
            for ( i in p0.children){
                var prodname1 = i.child("prodname").getValue()
                var prodprice1 = i.child("prodprice").getValue()
                sbname.append("$prodname1")
                sbprice.append("$prodprice1€")
            }
            textViewTuotenimiKO1.setText(sbname)
            textViewHintaKO1.setText(sbprice)
        }

    }

}
