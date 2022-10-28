package com.prod.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.navigation.NavigationBarView



class Diccionary : AppCompatActivity() {

    private lateinit var txtBuscar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diccionary)
        txtBuscar = findViewById<EditText>(R.id.txtBuscar)
        var button_nav = findViewById<NavigationBarView>(R.id.button_nav)

        button_nav.selectedItemId = R.id.ic_serach
        button_nav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.ic_serach-> true
                R.id.ic_diccionary -> {startActivity(Intent(this, MainSearch::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_favorite -> {startActivity(Intent(this, favRegister::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_profile -> {startActivity(Intent(this,userPerfile::class.java))
                    overridePendingTransition(0,0)}
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
    fun userPer(view:View){
        startActivity(Intent(this,userPerfile::class.java))
    }
    fun listRe(view:View){
        startActivity(Intent(this, MainSearch::class.java))
    }
    fun favorite(view:View){
        startActivity(Intent(this, favRegister::class.java))
    }
    fun buscar(view: View){
        val palabra:String=txtBuscar.text.toString()
        val intent = Intent(this, detailSearch::class.java)
        intent.putExtra("word", palabra)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

}