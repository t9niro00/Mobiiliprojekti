package com.example.elecstore

// Määritellään muuttujat, joita käytetään databasessa

class RealtimeDatabase {

    var prodnum = 0
    var prodname = ""
    var prodprice = 0.0
    var prodval = 0
    var prodimg = ""

    constructor()
    constructor(prodname: String, prodprice: Double, prodnum: Int, prodval: Int)
    {
        this.prodnum = prodnum
        this.prodprice = prodprice
        this.prodname = prodname
        this.prodval = prodval
        this.prodimg = prodimg

    }

}



