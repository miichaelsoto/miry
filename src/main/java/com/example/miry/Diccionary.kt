package com.example.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Diccionary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diccionary)
    }
    public override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }
    fun find(view:View){
        startActivity(Intent(this, MainSearch::class.java))
    }
    fun userPer(view:View){
        startActivity(Intent(this,userPerfile::class.java))
    }
    fun listRe(view:View){
        startActivity(Intent(this,Diccionary::class.java))
    }
    fun favorite(view:View){
        startActivity(Intent(this,favRegister::class.java))
    }
    fun callAbogado(view:View){
        startActivity(Intent(this,showAbogado::class.java))
    }

    fun callAbril(view:View){
        startActivity(Intent(this,showAbril::class.java))
    }

    fun callAbuela(view:View){
        startActivity(Intent(this,showAbuela::class.java))
    }

    fun callAbuelo(view:View){
        startActivity(Intent(this,showAbuelo::class.java))
    }
    fun callAca(view:View){
        startActivity(Intent(this,Diccionary::class.java))
    }
    fun callAdemas(view:View){
        startActivity(Intent(this, MainSearch::class.java))
    }
    fun callAdios(view:View){
        startActivity(Intent(this,userPerfile::class.java))
    }
    fun callAguaPura(view:View){
        startActivity(Intent(this,Diccionary::class.java))
    }
    fun callAprender(view:View){
        startActivity(Intent(this,favRegister::class.java))
    }
    fun callAqui(view:View){
        startActivity(Intent(this,showAbogado::class.java))
    }

    fun callBaño(view:View){
        startActivity(Intent(this,showAbril::class.java))
    }

    fun callBien(view:View){
        startActivity(Intent(this,showAbuela::class.java))
    }

    fun callBuenasN(view:View){
        startActivity(Intent(this,showAbuelo::class.java))
    }

    fun callBuenasT(view:View){
        startActivity(Intent(this,showAbuelo::class.java))
    }
}