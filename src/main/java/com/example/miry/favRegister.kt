package com.example.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class favRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_register)
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
}