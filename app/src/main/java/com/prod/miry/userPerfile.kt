package com.prod.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class userPerfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_perfile)
        // Initialize Firebase Auth
        auth= FirebaseAuth.getInstance()

       /* val user = auth.currentUser
        val actualuser=findViewById<TextView>(R.id.userName)
        val userName : String
        userName = user?.displayName.toString()
        actualuser.text = userName*/
        // barar de navegacion
        var button_nav = findViewById<NavigationBarView>(R.id.button_nav)

        button_nav.selectedItemId = R.id.ic_profile
        button_nav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.ic_serach-> {startActivity(Intent(this,Diccionary::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_diccionary -> {startActivity(Intent(this, MainSearch::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_favorite -> {startActivity(Intent(this, favRegister::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_profile -> true
            }
            true
        }
    }
    public override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }
    fun find(view:View){
        startActivity(Intent(this, Diccionary::class.java))
    }

    fun listRe(view:View){
        startActivity(Intent(this, MainSearch::class.java))
    }
    fun favorite(view:View){
        startActivity(Intent(this,favRegister::class.java))
    }
    fun closeSession(view: View){
        signOut()
    }
    fun goInfo(view: View){
        startActivity(Intent(this, acercaApp::class.java))
    }
    fun goContact(view: View){
        startActivity(Intent(this, contact::class.java))
    }
    fun goDonar(view: View){
        startActivity(Intent(this, donar::class.java))
    }
    private fun signOut(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, loginActivity::class.java))

    }
}