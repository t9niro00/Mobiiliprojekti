package com.example.elecstore

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeDatabase {
    var prodname = ""
    var prodprice = 0
    var prodnum = 0
    constructor(prodname:String,prodprice:Int,prodnum:Int){
        this.prodname = prodname
        this.prodprice = prodprice
        this.prodnum = prodnum
    }
}