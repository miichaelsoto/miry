package com.prod.miry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth


class forgotPassword : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        txtEmail=findViewById(R.id.txtEmail)
        auth= FirebaseAuth.getInstance()
    }
    fun reboot(view: View){
        rebootPass()
    }
    private fun rebootPass(){
        val e:String=txtEmail.text.toString()

        if (!TextUtils.isEmpty(e)){
            auth.sendPasswordResetEmail(e)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) Toast.makeText(this, "Email Enviado a $e", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "No se encontró el usuario con este correo", Toast.LENGTH_SHORT).show()
                }
        }
        else Toast.makeText(this, "Indica un email", Toast.LENGTH_SHORT).show()
    }
    }
