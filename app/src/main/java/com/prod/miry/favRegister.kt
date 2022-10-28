package com.prod.miry

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationBarView
import com.prod.miry.base.UserFavorites
import kotlinx.coroutines.launch

val Context.dataStore2 by preferencesDataStore("USER_FAVORITES2")
class favRegister : AppCompatActivity() {
    val listFav=ArrayList<String>(5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_register)
        val a = findViewById<TextView>(R.id.a)
        val arrayAdapter: ArrayAdapter<*>
        var mListView = findViewById<ListView>(R.id.lisFavId)
        var button_nav = findViewById<NavigationBarView>(R.id.button_nav)

        button_nav.selectedItemId = R.id.ic_favorite
        button_nav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.ic_serach-> {startActivity(Intent(this,Diccionary::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_diccionary -> {startActivity(Intent(this, MainSearch::class.java))
                    overridePendingTransition(0,0)}
                R.id.ic_favorite -> true
                R.id.ic_profile ->  {startActivity(Intent(this, userPerfile::class.java))
                    overridePendingTransition(0,0)}
            }
            true
        }

        //corrutina para dataStore
        lifecycleScope.launch(Dispatchers.IO) {
            //obtiene palabra de dataStore (almacenada en palabraVideo)
            getUserFav().collect{
                //selecciona el contexto principal
                withContext(Dispatchers.Main){
                    //asignan la palabra guardada en la primer posicion de la lista
                    listFav.add(0,it.palabra)
                }
            }
        }
        //if (listFav[0].isNotEmpty()) llenaData2()
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listFav)
        mListView.adapter = arrayAdapter

    }
    //Verifica si datastore 2 tiene informacion
    private fun llenaData2() {
        lifecycleScope.launch(Dispatchers.IO) {
            //obtiene palabra de dataStore (almacenada en palabraVideo)
            getUserFav2().collect{
                //selecciona el contexto principal
                withContext(Dispatchers.Main){
                    //asignan la palabra guardada
                    listFav.add(1,it.palabra)
                }
            }
        }
       /* if (listFav[1].isEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                saveValues(listFav[0])

                getUserFav2().collect{
                    //selecciona el contexto principal
                    withContext(Dispatchers.Main){
                        //asignan la palabra guardada
                        listFav.add(1,it.palabra)
                        listFav.removeAt(0)
                    }
                }
            }
        }*/
    }

    private fun getUserFav()=dataStore.data.map{it->
        //UserFavorites es la data class
         UserFavorites(
             //asigna a palabra lo almacenado con la stringpreferencekey FavPal, es la misma de palabra video
             palabra = it[stringPreferencesKey("FavPal")].orEmpty()
         )

    }
    private fun getUserFav2()=dataStore2.data.map{it->
        //UserFavorites es la data class
        UserFavorites(
            //asigna a palabra lo almacenado con la stringpreferencekey FavPal, es la misma de palabra video
            palabra = it[stringPreferencesKey("FavPal")].orEmpty()
        )

    }
    private suspend fun saveValues(newFavPalabra: String) {
        dataStore2.edit { it->
            it[stringPreferencesKey("FavPal")]=newFavPalabra
        }
    }
    //subrutina para que al dar atras se salga de la app
    public override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
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
    /*fun favorite(view:View){
        startActivity(Intent(this,favRegister::class.java))
    }*/
        //llama a palabra video para mostrar el GIF y su detalle
        fun goGif(view: View) {

            if (intent.extras != null) {
                val newList = palabraProvider.diccionary

                val searchP = intent.getStringExtra(
                    "word"
                )
                val res = newList.filter { it.palabra == searchP }
                val res1: palabraDiccionario = res.toList().get(0)
                val palabra: String = res1.palabra
                val url: String = res1.url
                val descripcion: String = res1.descripcion
                val intent = Intent(this, palabraVideo::class.java)
                intent.putExtra("word", palabra)
                intent.putExtra("img", url)
                intent.putExtra("desc", descripcion)
                startActivity(intent)
            }
        }
}