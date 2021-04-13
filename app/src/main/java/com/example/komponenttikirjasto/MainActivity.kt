package com.example.komponenttikirjasto


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elecstore.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

//Setting the data
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database = FirebaseDatabase.getInstance().reference

        var prodnum = 2
        var prodname = "Tuote"
        var prodprice = 6969



        database.child(prodnum.toString()).setValue(RealtimeDatabase(prodname,prodprice,prodnum))
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }

    // Getting the data

    var getData = object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            var sb = StringBuilder()
            for ( i in p0.children){
                var prodname1 = i.child("prodname").getValue()
                var prodprice1 = i.child("prodprice").getValue()
                sb.append("${i.key} $prodname1 $prodprice1" )
            }
            editTextSearch.setText(sb)

        }

    }

}
