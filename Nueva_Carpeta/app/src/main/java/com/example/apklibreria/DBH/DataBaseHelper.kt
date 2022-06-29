package com.example.apklibreria.DBH

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apklibreria.Usuario.UsuarioTabla



class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,
    UsuarioTabla.Companion.Tabla.NombreTabla,null,
    UsuarioTabla.VERSION
){
    companion object{
        var TablaUsers = "CREATE TABLE ${UsuarioTabla.Companion.Tabla.NombreTabla} (${UsuarioTabla.Companion.Tabla.ColumnaDNI} TEXT PRIMARY KEY, " +
                "${UsuarioTabla.Companion.Tabla.ColumnaNombre} TEXT ," + "${UsuarioTabla.Companion.Tabla.ColumnaApellido} TEXT ," +
                 "${UsuarioTabla.Companion.Tabla.ColumnaPassword} TEXT ,"+
                "${UsuarioTabla.Companion.Tabla.ColumnaPassword2} TEXT )"

        val RemoveTablaUsers = "DROP TABLE IF EXISTS ${UsuarioTabla.Companion.Tabla.NombreTabla}"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TablaUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(RemoveTablaUsers)
        onCreate(db)
    }
}








