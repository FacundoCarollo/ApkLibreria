package com.example.apklibreria.Usuario

import android.provider.BaseColumns

class UsuarioTabla() {

    companion object {

        val VERSION = 1

        class Tabla: BaseColumns{
            companion object {
                val NombreTabla = "usuarios"
                val ColumnaDNI= "dni"
                val ColumnaNombre = "nombre"
                val ColumnaApellido= "apellido"
                val ColumnaPassword = "password"
                val ColumnaPassword2 = "Repetir_Password"
            }
        }
    }
}