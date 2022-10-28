package com.prod.miry

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import com.prod.miry.base.RecyclerAdapter



class MainSearch : AppCompatActivity(),RecyclerAdapter.onItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search)
        setupRecyclerView()
        var button_nav = findViewById<NavigationBarView>(R.id.button_nav)
        button_nav.selectedItemId=R.id.ic_diccionary

        button_nav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.ic_serach-> {startActivity(Intent(this, Diccionary::class.java))
                    overridePendingTransition(0,0)}

                R.id.ic_diccionary -> true
                R.id.ic_favorite -> {startActivity(Intent(this, favRegister::class.java))
                    overridePendingTransition(0,0)}

                R.id.ic_profile -> {startActivity(Intent(this, userPerfile::class.java))
                    overridePendingTransition(0,0)}

            }
            true
        }

    }


    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerid)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(this, palabraProvider.diccionary, this)


    }


    public override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    fun find(view: View) {
        startActivity(Intent(this, Diccionary::class.java))
    }

    fun userPer(view: View) {
        startActivity(Intent(this, userPerfile::class.java))
    }

    fun favorite(view: View) {
        startActivity(Intent(this, favRegister::class.java))
    }

    override fun onPalabraClick(palabra: String, urlImg: String, descripcion: String) {
        val intent = Intent(this, palabraVideo::class.java)
        intent.putExtra("word", palabra)
        intent.putExtra("img", urlImg)
        intent.putExtra("desc", descripcion)
        startActivity(intent)

    }
}
