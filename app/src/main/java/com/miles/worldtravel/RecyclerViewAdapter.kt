package com.miles.worldtravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class RecyclerViewAdapter(private val data: ArrayList<Place>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tv_place = v.findViewById<TextView>(R.id.place)
        val tv_rank = v.findViewById<TextView>(R.id.rank)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_place.text = data[position].place.toString()
        holder.tv_rank.text = data[position].rank.toString()

    }
}