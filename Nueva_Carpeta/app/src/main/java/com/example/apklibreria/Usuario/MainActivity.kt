package com.example.apklibreria.Usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.apklibreria.R

import com.example.Usuario.Usuario


class MainActivity : AppCompatActivity() {
    var metodos: UsuarioMetodos? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dni = findViewById<EditText>(R.id.txtDNI)
        val nombre = findViewById<EditText>(R.id.txtNombre)
        val apellido = findViewById<EditText>(R.id.txtApellido)
        val password =  findViewById<EditText>(R.id.txtPassword)
        val repetirPassword = findViewById<EditText>(R.id.txtRepetirPassword)
        val crearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val inciarSesion = findViewById<Button>(R.id.btnIniciarLoginHorizontal)


        metodos = UsuarioMetodos(this)

        crearCuenta.setOnClickListener{
            if  (dni.text.toString() != "" || nombre.text.toString() != ""
                || apellido.text.toString() != ""
                || password.text.toString() != "" || repetirPassword.text.toString() != "" &&
                password.text.toString() == repetirPassword.text.toString()
            ) {
                metodos?.AgregarUsuario(
                    Usuario(dni.text.toString(),
                        nombre.text.toString(),
                        apellido.text.toString(),
                        password.text.toString(),
                        repetirPassword.text.toString()
                    )
                )
                startActivity(Intent(this, Login::class.java))
            }


            if(password.text.toString() != repetirPassword.text.toString()){
                Toast.makeText(this,"Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
           }

            if(dni.text.toString().length < 8){
                Toast.makeText(this,"Ingrese un DNI valido", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }

             if (nombre.text.toString().length < 3){

                Toast.makeText(this,"El nombre debe ser mayor a dos caracteres", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }

            if (apellido.text.toString().length < 3){

                Toast.makeText(this,"El apellido debe ser mayor a tres caracteres", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            if (password.text.toString().length < 3){

                Toast.makeText(this,"La contraseña debe ser mayor a tres caracteres", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            if (repetirPassword.text.toString().length < 3){

                Toast.makeText(this,"La contraseña debe ser mayor a tres caracteres", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }

            else if(dni.text.toString().isEmpty() || nombre.text.toString().isEmpty()
                || apellido.text.toString().isEmpty()
                || password.text.toString().isEmpty()
                || repetirPassword.text.toString().isEmpty()){

                Toast.makeText(this,"Complete los campos", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        inciarSesion.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
    }
}