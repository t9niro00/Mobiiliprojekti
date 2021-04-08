package com.example.elecstore

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeDatabase {
    val database = Firebase.database
    val myRef = database.getReference("Message")

}