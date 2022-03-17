package com.miles.worldtravel

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class SecondActivity : AppCompatActivity()  {
    lateinit var decoration:RecyclerViewItemSpace
    lateinit var rv_rank: RecyclerView
    private var placeList = ArrayList<Place>()
    private lateinit var viewAdapter: SecondRecyclerViewAdapter
    lateinit var tv_result_day: TextView
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        decoration = RecyclerViewItemSpace()
        rv_rank = findViewById<RecyclerView>(R.id.rv_rank_second)
        viewAdapter = SecondRecyclerViewAdapter(placeList)
        rv_rank.addItemDecoration(decoration)
        rv_rank.layoutManager = LinearLayoutManager(this)
        rv_rank.adapter = viewAdapter
        tv_result_day = findViewById(R.id.tv_result_day)
        val btn_back = findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener {

        }
    }
}