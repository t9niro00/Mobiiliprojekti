package com.example.elecstore.DatabaseKoodi


import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.FileInputStream


class DB {

    lateinit var storage: FirebaseStorage

}
fun uploadtesti(){

    val storage = Firebase.storage


    val storageRef = storage.reference

    val mountainsRef = storageRef.child("mountains.jpg")

    val mountainImagesRef = storageRef.child("Images/mountains.jpg")

    mountainsRef.name == mountainImagesRef.name //True
    mountainsRef.path == mountainImagesRef.path //false

    val stream = FileInputStream(File("Documents/mountains.jp"))

    var uploadTask = mountainsRef.putStream(stream)
    uploadTask.addOnFailureListener {
        // Handle unsuccessful uploads
    }.addOnSuccessListener { taskSnapshot ->

    }


}
