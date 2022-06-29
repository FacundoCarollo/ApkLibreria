package com.example.Usuario

class Usuario(dni:String?, nombre:String, apellido:String,password:String,password2: String ) {

    var dni:String? = null
    var nombre:String? = null

    var apellido:String? = null

    var password:String? = null
    var password2:String? = null

    init {
        this.dni = dni
        this.nombre = nombre
       this.apellido = apellido
        this.password = password
        this.password2 = password2

    }
}