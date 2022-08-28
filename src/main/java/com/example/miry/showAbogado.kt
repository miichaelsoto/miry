package com.example.miry

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class showAbogado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_abogado)
        var vvLocal = findViewById<VideoView>(R.id.abogado)
        var mcLocal = MediaController(this)
        mcLocal.setAnchorView(vvLocal)
        var path = "android.resource://" + packageName + "/" + R.raw.prueba
        vvLocal.setVideoURI(Uri.parse(path))
        vvLocal.setMediaController(mcLocal)
        vvLocal.start()

    }

}