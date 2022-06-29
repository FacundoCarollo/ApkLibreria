package com.example.apklibreria.Usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.apklibreria.R

import com.example.apklibreria.Inicio.Inicio

class Login : AppCompatActivity() , View.OnClickListener {


    private  var txtdni: EditText? = null
    private  var txtPassword: EditText? = null
    private  var btnC: Button? = null
    private var btnL: Button? = null
    private  var db: UsuarioMetodos? = null
    private lateinit var dni : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inicializarViews()
        inicializarListeners()
        inicializarDB()
    }

    private fun inicializarViews() {

        txtdni = findViewById(R.id.txtDniLogin)
        txtPassword = findViewById(R.id.textPasswordLogin)
        btnC = findViewById(R.id.btnCrearCuentaLogin)
        btnL= findViewById(R.id.btnIniciarLogin)
    }

    private fun inicializarDB() {
        db = UsuarioMetodos(this)
    }

    private fun inicializarListeners() {
        btnL?.setOnClickListener(this)
        btnC?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){

            R.id.btnIniciarLogin -> UsuarioValidacion()

            R.id.btnCrearCuentaLogin ->{
                val mainActivity = Intent(applicationContext, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
    }

    private fun UsuarioValidacion() {

         dni = txtdni!!.text.toString()

        if ( dni == "") {
            Toast.makeText(this,"Ingrese su DNI ", Toast.LENGTH_SHORT).show()
        }

        if ( dni == "") {
            Toast.makeText(this,"Ingrese su password ", Toast.LENGTH_SHORT).show()
        }

        if (dni != "" && txtPassword!!.text.toString() != ""){

            if (db!!.VerificarUsuario(dni.trim { it <= ' ' }, txtPassword!!.text.toString().trim { it <= ' ' })) {
                val accountsIntent = Intent(this, Inicio::class.java)
                accountsIntent.putExtra("dni", dni.trim { it <= ' ' })
                textoVacio()
                startActivity(accountsIntent)
            }
            else{

                Toast.makeText(this,"Su DNI o Contraseña son incorrectos", Toast.LENGTH_SHORT).show()
            }
        }


















    }

    private fun textoVacio() {
        txtdni!!.text = null
        txtPassword!!.text = null
    }






}