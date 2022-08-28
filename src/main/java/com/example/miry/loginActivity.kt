package com.example.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {

            private lateinit var txtUsr: EditText
        private lateinit var txtPwd: EditText
        private lateinit var progressBar: ProgressBar
        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)
             txtUsr=findViewById(R.id.txtUsr)
            txtPwd=findViewById(R.id.txtPwd)
            progressBar=findViewById(R.id.progressBar)
            auth=FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser!=null){
            goHome(currentUser.email.toString(), currentUser.providerId)
        }

    }

    public override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)

    }
    fun register(view:View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    fun login(view: View){
        loginUser()
    }
    fun forgotPass(view: View){
        startActivity(Intent(this,forgotPassword::class.java))
    }
    private fun goHome(email: String, provider: String){
        var userEmail = email
        var providerSession = provider
        val intent = Intent(this,MainSearch::class.java)
        startActivity(intent)
    }
    private fun loginUser(){
        val user:String=txtUsr.text.toString()
        val password:String=txtPwd.text.toString()

        if (!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task->
                    if(task.isSuccessful){
                        goHome(user,"email")
                    }
                    else{
                        Toast.makeText(this,"Contraseña/Correo erroneo", Toast.LENGTH_LONG).show()
                    }

                }
        }
    }


}