package com.example.elecstore

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.komponenttikirjasto.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

// Määritellään muuttujat, joita käytetään databasessa

class RealtimeDatabase {

    var prodnum = 0
    var prodname = ""
    var prodprice = 0

    constructor()
    constructor(prodname: String, prodprice: Int, prodnum: Int)
    {
        this.prodnum = prodnum
        this.prodprice = prodprice
        this.prodname = prodname

    }

}



