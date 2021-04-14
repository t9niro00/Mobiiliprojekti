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

        var database = FirebaseDatabase.getInstance().reference

        var name = "Products"
        var prodnum = 3
        var prodname = "Kompo"
        var prodprice = 7



        database.child(name).setValue(RealtimeDatabase(prodname,prodprice,prodnum))
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }

    // Haetaan data

    var getData = object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            var sb = StringBuilder()
            for ( i in p0.children){
                var prodname1 = i.child("prodname").getValue()
                var prodnum1 = i.child("prodnum").getValue()

                sb.append("$prodname1,$prodnum1")
            }
            textViewTuotenimiKO1.setText(sb)

        }

    }

}
