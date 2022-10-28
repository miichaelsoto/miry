package com.prod.miry


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore("USER_FAVORITES")
class palabraVideo : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palabra_video)
        database = FirebaseDatabase.getInstance().getReference("Favoritos")
        progress=findViewById(R.id.progressBar)
        progress.visibility = View.VISIBLE
       if(intent.extras != null){

            val gif = findViewById<ImageView>(R.id.gif)
            val instruc = findViewById<TextView>(R.id.explanation2)
            val title = findViewById<TextView>(R.id.title)
            val titulo = intent.getStringExtra("word")
            val switch =findViewById<Switch>(R.id.add)
            val txtswitch=findViewById<TextView>(R.id.txtadd)
            var chgtxtswitch =""
            val titulo2=titulo.toString()
            var modifiedString = ""
            auth= FirebaseAuth.getInstance()
            val newList = palabraFav.diccionary.toMutableList()
            //convierte el titulo a mayusculas
             for (c in titulo2) {
                 modifiedString += when {
                     c.isLowerCase() -> c.toUpperCase()
                     c.isUpperCase() -> c.toLowerCase()
                     else -> c
                }
            }
            title.text = modifiedString
            //valida switch button y añade a favoritos
            if(switch.isChecked){
                chgtxtswitch="Añadido a Favoritos"
                txtswitch.text = chgtxtswitch
            }else{
                chgtxtswitch="Añadir a Favoritos"
                txtswitch.text = chgtxtswitch
            }
           //valida cambios en switch button
            switch?.setOnCheckedChangeListener({ _ , isChecked ->
                if (isChecked) {
                    chgtxtswitch = "Añadido a Favoritos"
                    txtswitch.text = chgtxtswitch
                    if (intent.extras != null) {
                        //obtiene palabra seleccionada
                        val newFavPalabra = intent.getStringExtra("word").toString()
                        //corrutina para usar el datastore
                        lifecycleScope.launch(Dispatchers.IO) {
                            saveValues(newFavPalabra)
                        }
                    } else {
                        chgtxtswitch = "Añadir a Favoritos"
                        txtswitch.text = chgtxtswitch
                        if (intent.extras != null) {
                            val searchP = intent.getStringExtra("word")
                            val cpylist = palabraFav.diccionary.filter { it.palabra != searchP }
                            palabraFav.diccionary.toMutableList().clear()
                            palabraFav.diccionary.toMutableList().addAll(cpylist)
                        }

                    }
                }
            })
            //Muestra imagen y progressbarr
            Glide.with(this)
                .load(intent.getStringExtra("img"))
                .listener(GlideImpl.OnCompleted {
                    progress.visibility = View.GONE
                })
                .into(gif)
            //agrega las instrucciones del video
            val instructions = intent.getStringExtra("desc")
            instruc.text = instructions
        }


    }
    //almacena la palabra en dataStore
    private suspend fun saveValues(newFavPalabra: String) {
        dataStore.edit { it->
            it[stringPreferencesKey("FavPal")]=newFavPalabra
        }
    }


    object GlideImpl {

        object OnCompleted : RequestListener<Drawable> {

            private lateinit var onComplete: () -> Unit

            operator fun invoke(onComplete: () -> Unit): OnCompleted {
                OnCompleted.onComplete = { onComplete() }
                return this
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onComplete()
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onComplete()
                return false
            }
        }
    }



}