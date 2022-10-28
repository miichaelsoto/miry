package com.prod.miry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.navigation.NavigationBarView

class detailSearch : AppCompatActivity() {
    private lateinit var showP: TextView
    private lateinit var resul: RelativeLayout
    private lateinit var showN: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_search)

        showP = findViewById(R.id.showP)
        showN = findViewById(R.id.showN)
        resul = findViewById(R.id.resul)

        if (intent.extras != null) {
            val newList = palabraProvider.diccionary

            val searchP = intent.getStringExtra(
                "word"
            )
            //convierte mayusculas a minusculas antes
            var modifiedString = ""
            for (c in searchP.toString()) {
                modifiedString += when {
                    c.isUpperCase() -> c.toLowerCase()
                    else -> c
                }
            }
            val res = newList.filter { it.palabra == modifiedString }
            val reslen=res.size

            if(reslen!=0) {
                val res1: palabraDiccionario = res.toList().get(0)
                showP.text = res1.palabra
            }else {
                resul.visibility = View.GONE
                showN.visibility = View.VISIBLE
                showN.text = "No se encontró nada"

            }
        }

        var button_nav = findViewById<NavigationBarView>(R.id.button_nav)
        button_nav.selectedItemId=R.id.ic_serach

        button_nav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.ic_serach-> {startActivity(Intent(this, Diccionary::class.java))
                    overridePendingTransition(0,0)}

                R.id.ic_diccionary ->  {startActivity(Intent(this, MainSearch::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_favorite -> {startActivity(Intent(this, favRegister::class.java))
                    overridePendingTransition(0,0)}

                R.id.ic_profile -> {startActivity(Intent(this, userPerfile::class.java))
                    overridePendingTransition(0,0)}

            }
            true
        }
    }

    fun goGif(view: View) {

        if (intent.extras != null) {
            val newList = palabraProvider.diccionary

            val searchP = intent.getStringExtra(
                "word"
            )
            val res = newList.filter { it.palabra == searchP }
            val res1: palabraDiccionario = res.toList().get(0)
            showP.text = res1.palabra

            val palabra: String = res1.palabra
            val url: String = res1.url
            val descripcion: String =res1.descripcion
            val intent = Intent(this, palabraVideo::class.java)
            intent.putExtra("word", palabra)
            intent.putExtra("img", url)
            intent.putExtra("desc", descripcion)
            startActivity(intent)
        }

    }
    fun find(view:View){
        startActivity(Intent(this, Diccionary::class.java))
    }
    fun userPer(view:View){
        startActivity(Intent(this,userPerfile::class.java))
    }
    fun listRe(view:View){
        startActivity(Intent(this, MainSearch::class.java))
    }
    fun favorite(view:View){
        startActivity(Intent(this,favRegister::class.java))
    }
}