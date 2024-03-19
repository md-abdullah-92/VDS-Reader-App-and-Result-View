package com.example.lasttry

import java.security.PrivateKey

data class VDSCreator(
    val name: String,
    val password: String,
    var email: String,
    var mobile : String
)
data class VDSdata(
    val name: String,
    var email: String,
    var publickey:String,
    var privateKey:String
)
