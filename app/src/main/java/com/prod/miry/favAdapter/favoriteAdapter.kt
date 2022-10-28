package com.prod.miry.favAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prod.miry.R
import com.prod.miry.favoritosModel

class favoriteAdapter(private val palabralist:ArrayList<favoritosModel>) :
    RecyclerView.Adapter<favoriteAdapter.ViewHolder>(){
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):ViewHolder{
        val itemView = (LayoutInflater.from(parent.context).inflate(R.layout.fav_item,parent,false))
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder:favoriteAdapter.ViewHolder, position: Int){
         val currentFavPal =palabralist[position]
        holder.tvFav.text=currentFavPal.palabra
    }
    override fun getItemCount(): Int {
        return palabralist.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvFav : TextView=itemView.findViewById(R.id.tvFav)
    }
}