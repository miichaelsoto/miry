package com.prod.miry.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prod.miry.R
import com.prod.miry.palabraDiccionario

class RecyclerAdapter(
    private val context: Context,
    private val listaPalabras:List<palabraDiccionario>,
    private val itemClickListener:onItemClickListener
    ):RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onItemClickListener{
        fun onPalabraClick(palabra: String, urlImg: String, descripcion: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return PalabrasViewHolder(LayoutInflater.from(context).inflate(R.layout.item_word,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PalabrasViewHolder -> holder.bind(listaPalabras[position],position)
        }
    }

    override fun getItemCount(): Int = listaPalabras.size

    inner class PalabrasViewHolder(itemView: View):BaseViewHolder<palabraDiccionario>(itemView){
        override fun bind(item: palabraDiccionario, position: Int) {

            itemView.setOnClickListener { itemClickListener.onPalabraClick(item.palabra,item.url, item.descripcion) }
            val texto = itemView.findViewById<TextView>(R.id.theWord)
            texto.text = item.palabra

        }
    }
}