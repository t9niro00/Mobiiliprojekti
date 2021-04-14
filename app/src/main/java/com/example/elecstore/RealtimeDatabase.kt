package com.example.elecstore

// Määritellään muuttujat, joita käytetään databasessa

class RealtimeDatabase {
    var prodname = ""
    var prodprice = 0
    var prodnum = 0
    constructor(prodname:String, prodprice:Int, prodnum:Int){
        this.prodname = prodname
        this.prodprice = prodprice
        this.prodnum = prodnum
    }
}