package com.example.apklibreria.Usuario


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.Usuario.Usuario
import com.example.apklibreria.DBH.DataBaseHelper
import java.util.*
class UsuarioMetodos(context: Context) {

    private var dbhelper: DataBaseHelper? = null

    init {
        dbhelper = DataBaseHelper(context)
    }


    fun AgregarUsuario(item: Usuario) {
        val db: SQLiteDatabase = dbhelper?.writableDatabase!!
        val values = ContentValues()
        values.put(UsuarioTabla.Companion.Tabla.ColumnaDNI, item.dni)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaNombre, item.nombre)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaApellido, item.apellido)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaPassword, item.password)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaPassword2, item.password)

        db.insert(UsuarioTabla.Companion.Tabla.NombreTabla,null,values)
        db.close()
    }



    fun VerificarUsuario(dni: String, password: String): Boolean {

        val columns = arrayOf(UsuarioTabla.Companion.Tabla.ColumnaDNI , UsuarioTabla.Companion.Tabla.ColumnaPassword)
        val db:SQLiteDatabase = dbhelper?.readableDatabase!!
        val seleccionar = "${UsuarioTabla.Companion.Tabla.ColumnaDNI}= ? AND ${UsuarioTabla.Companion.Tabla.ColumnaPassword} = ?"
        val seleccionarArgs = arrayOf(dni, password)
        val cursor = db.query(
            UsuarioTabla.Companion.Tabla.NombreTabla,
            columns,
            seleccionar,
            seleccionarArgs,
            null,
            null,
            null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }


    fun MostrarUsuario(dni:String): Usuario {

        var item: Usuario? = null


        val db: SQLiteDatabase = dbhelper?.readableDatabase!!


        val columns = arrayOf(UsuarioTabla.Companion.Tabla.ColumnaDNI, UsuarioTabla.Companion.Tabla.ColumnaNombre, UsuarioTabla.Companion.Tabla.ColumnaApellido,UsuarioTabla.Companion.Tabla.ColumnaPassword, UsuarioTabla.Companion.Tabla.ColumnaPassword2 )


        val c:Cursor = db.query(
            UsuarioTabla.Companion.Tabla.NombreTabla,
            columns,
            " dni = ?",
            arrayOf(dni),
            null,
            null,
            null
        )

        while (c.moveToNext()) {
            item = Usuario(c.getString(c.getColumnIndexOrThrow(UsuarioTabla.Companion.Tabla.ColumnaDNI)),
                c.getString(c.getColumnIndexOrThrow(UsuarioTabla.Companion.Tabla.ColumnaNombre)),
                c.getString(c.getColumnIndexOrThrow(UsuarioTabla.Companion.Tabla.ColumnaApellido)),
                c.getString(c.getColumnIndexOrThrow(UsuarioTabla.Companion.Tabla.ColumnaPassword)),
                c.getString(c.getColumnIndexOrThrow(UsuarioTabla.Companion.Tabla.ColumnaPassword2))

            )
        }
        c.close()

        return item!!
    }
    fun ActualizarUsuario(dni: String, password: String, password2: String): Boolean {

        val db: SQLiteDatabase = dbhelper?.writableDatabase!!

        val values = ContentValues()
        values.put(UsuarioTabla.Companion.Tabla.ColumnaDNI, dni)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaPassword, password)
        values.put(UsuarioTabla.Companion.Tabla.ColumnaPassword2, password2)
        db.update(UsuarioTabla.Companion.Tabla.NombreTabla, values, "dni = ?", arrayOf(dni))

        return true

    }




























}