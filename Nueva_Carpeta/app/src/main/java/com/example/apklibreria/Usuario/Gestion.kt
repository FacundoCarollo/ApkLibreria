package com.example.apklibreria.Usuario

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast


import com.example.apklibreria.Inicio.Inicio
import com.example.apklibreria.R

class Gestion : AppCompatActivity() {



    var usuarioMetodos: UsuarioMetodos?=  null
    private  lateinit  var dni : TextView
    private  lateinit  var nombre : TextView
    private  lateinit  var apellido : TextView
    private  lateinit var modificar : Button
    private  lateinit var dniUsuario : String
    private  lateinit var password : EditText
    private  lateinit var password2 : EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gestion)

        supportActionBar?.title = "Usuarios"


        dni = findViewById(R.id.txtViewDni)
        nombre = findViewById(R.id.txtViewNombre)
        apellido = findViewById(R.id.txtViewApellido)
        modificar = findViewById(R.id.cambiar_Password)
        password = findViewById(R.id.cambio_password)
        password2 = findViewById(R.id.cambio_repetirPassword)

        dniUsuario = intent.getStringExtra("dni").toString()

        usuarioMetodos = UsuarioMetodos(this)

        val usuario = usuarioMetodos?.MostrarUsuario(dniUsuario)
        dni.setText(usuario?.dni, TextView.BufferType.EDITABLE)
        nombre.setText(usuario?.nombre, TextView.BufferType.EDITABLE)
        apellido.setText(usuario?.apellido, TextView.BufferType.EDITABLE)


        modificar.setOnClickListener {

              if (password.text.toString() != "" && password.text.toString() != "" && password.text.toString() == password2.text.toString() && password.text.toString().length > 3 && password2.text.toString().length > 3) {

                      usuarioMetodos?.ActualizarUsuario(
                          dniUsuario,
                          password.text.toString(),
                          password2.text.toString()
                      )
                  val intentLogin = Intent(this, Login::class.java)
                  startActivity(intentLogin)

              }

              if(password.text.toString().length < 3 || password2.text.toString().length < 3){

                  Toast.makeText(this,"La contraseña debe ser mayor a tres caracteres", Toast.LENGTH_LONG).show()
                  val intentGestion = Intent(this, Gestion::class.java)
                  intentGestion.putExtra("dni", dniUsuario)
                  startActivity(intentGestion)

             }
            if(password.text.toString() != password2.text.toString()){

                Toast.makeText(this,"La contraseña deben ser iguales", Toast.LENGTH_LONG).show()
                val intentGestion = Intent(this, Gestion::class.java)
                intentGestion.putExtra("dni", dniUsuario)
                startActivity(intentGestion)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val intentGestion = Intent(this, Gestion::class.java)

        intentGestion.putExtra("dni", dniUsuario)

        val intentInicio = Intent(this, Inicio::class.java)
        intentInicio.putExtra("dni", dniUsuario)

        when(item.itemId){
            R.id.inicio -> startActivity(intentInicio)
            R.id.usuarios -> startActivity(intentGestion)
            R.id.salir -> startActivity(Intent(this,MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}































