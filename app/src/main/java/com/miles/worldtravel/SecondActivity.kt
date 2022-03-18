package com.miles.worldtravel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
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
    lateinit var rv_rank_second: RecyclerView
    private var placeList = ArrayList<Place>()
    private lateinit var viewAdapterSecond: SecondRecyclerViewAdapter
    lateinit var tv_result_day: TextView
    lateinit var tv_result_consume_day: TextView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //MainActivity.mainActivity.mActivity.finish()
        Log.e("jump","已跳轉")
        decoration = RecyclerViewItemSpace()
        rv_rank_second = findViewById<RecyclerView>(R.id.rv_rank_second)
        viewAdapterSecond = SecondRecyclerViewAdapter(placeList)
        rv_rank_second.addItemDecoration(decoration)
        rv_rank_second.layoutManager = LinearLayoutManager(this)
        rv_rank_second.adapter = viewAdapterSecond
        tv_result_day = findViewById(R.id.tv_result_day)
        tv_result_consume_day = findViewById(R.id.tv_result_consume_day)
        intent?.extras?.let{
            var b = it.getBundle("bundle")
            try{
                Log.e("placeList[0]",placeList[0].toString())
            }catch (e:Exception){
                Log.e("e",e.toString())
            }
            var tempList = b?.getParcelableArrayList<Place>("schedule") as ArrayList<Place>
            for(i in tempList){
                Log.e("i",i.toString())
                for(j in i.place){
                    if (j=='('){
                        i.place = i.place.substring(0,i.place.indexOf(j))
                    }
                }
                placeList.add(i)
            }

            viewAdapterSecond.notifyDataSetChanged()
            //Log.e("placeList[0]",placeList[0].toString())
            //Log.e("placeList",placeList.size.toString())
            var day = b?.getString("day")
            tv_result_day.setText("選擇的旅遊天數: $day")
            var sum = b?.getString("sum")
            tv_result_consume_day.setText("選擇的行程花費總天數: $sum")

        }
        val btn_back = findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener {
            //var mAct = MainActivity()
            val intent = Intent(this,MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //var mAct = MainActivity()
        val intent = Intent(this,MainActivity::class.java)
        finish()
        startActivity(intent)
    }

}