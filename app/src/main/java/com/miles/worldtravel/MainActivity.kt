package com.miles.worldtravel

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize
import java.lang.Exception
import java.lang.Integer.max
import java.util.stream.IntStream.range

class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerViewAdapter
    private var placeList = ArrayList<Place>()
    private var mutPlaceList = mutableListOf<String>()
    private var expect = 1
    private var score = 120
    lateinit var decoration:RecyclerViewItemSpace
    lateinit var rv_rank: RecyclerView
    lateinit var cb_us: CheckBox
    lateinit var cb_rus: CheckBox
    lateinit var cb_cn: CheckBox
    lateinit var cb_aus: CheckBox
    lateinit var cb_af: CheckBox
    lateinit var cb_uk: CheckBox
    lateinit var cb_eu: CheckBox
    lateinit var cb_in: CheckBox
    lateinit var us: String
    lateinit var rus: String
    lateinit var cn: String
    lateinit var aus: String
    lateinit var af: String
    lateinit var uk: String
    lateinit var eu: String
    lateinit var ind: String
    var day = 0


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decoration = RecyclerViewItemSpace()
        rv_rank = findViewById<RecyclerView>(R.id.rv_rank)
        cb_us = findViewById<CheckBox>(R.id.cb_us)
        cb_rus = findViewById<CheckBox>(R.id.cb_rus)
        cb_cn = findViewById<CheckBox>(R.id.cb_cn)
        cb_aus = findViewById<CheckBox>(R.id.cb_aus)
        cb_af = findViewById<CheckBox>(R.id.cb_af)
        cb_uk = findViewById<CheckBox>(R.id.cb_uk)
        cb_eu = findViewById<CheckBox>(R.id.cb_eu)
        cb_in = findViewById<CheckBox>(R.id.cb_in)
        us = cb_us.text.toString()
        rus = cb_rus.text.toString()
        cn = cb_cn.text.toString()
        aus = cb_aus.text.toString()
        af = cb_af.text.toString()
        uk = cb_uk.text.toString()
        eu = cb_eu.text.toString()
        ind = cb_in.text.toString()
        mutPlaceList.add("test")
        //創建 MyRecyclerAdapter 並連結 recyclerView
        viewAdapter = RecyclerViewAdapter(placeList)
        rv_rank.addItemDecoration(decoration)
        rv_rank.layoutManager = LinearLayoutManager(this)
        rv_rank.adapter = viewAdapter
        val btn_send = findViewById<Button>(R.id.btn_send)
        val ed_day = findViewById<EditText>(R.id.ed_day)
        btn_send.setOnClickListener {
            try {
                this.day = ed_day.text.toString().toInt()
            }catch (e: Exception){
                Toast.makeText(this,"請輸入天數",Toast.LENGTH_SHORT).show()
            }

            if(ed_day.length()<1 || this.day == 0){
                Toast.makeText(this,"請輸入天數",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(placeList.isEmpty()){
                Toast.makeText(this,"請選擇景點",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            calculate()
        }

    }

    override fun onStart() {
        super.onStart()

        cb_us.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.us !in this.mutPlaceList){
                    this.mutPlaceList.add(this.us)
                    this.placeList.add(Place(us,0, 0,15))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("mutablePlaceList", mutPlaceList.toString())
                    Log.e("placeList", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.us in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.us)-1])
                    this.mutPlaceList.remove(this.us)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("mutablePlaceList", mutPlaceList.toString())
                    Log.e("placeList", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_uk.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.uk !in this.mutPlaceList){
                    this.mutPlaceList.add(this.uk)
                    this.placeList.add(Place(uk,0, 0,12))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("mutablePlaceList", mutPlaceList.toString())
                    Log.e("placeList", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.uk in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.uk)-1])
                    this.mutPlaceList.remove(this.uk)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("mutablePlaceList", mutPlaceList.toString())
                    Log.e("placeList", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_rus.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.rus !in this.mutPlaceList){
                    this.mutPlaceList.add(this.rus)
                    this.placeList.add(Place(rus,0, 0,20))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.rus in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.rus)-1])
                    this.mutPlaceList.remove(this.rus)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_in.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.ind !in this.mutPlaceList){
                    this.mutPlaceList.add(this.ind)
                    this.placeList.add(Place(ind,0, 0,9))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.ind in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.ind)-1])
                    this.mutPlaceList.remove(this.ind)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_eu.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.eu !in this.mutPlaceList){
                    this.mutPlaceList.add(this.eu)
                    this.placeList.add(Place(eu,0, 0,11))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.eu in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.eu)-1])
                    this.mutPlaceList.remove(this.eu)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_cn.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.cn !in this.mutPlaceList){
                    this.mutPlaceList.add(this.cn)
                    this.placeList.add(Place(cn,0, 0,12))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.cn in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.cn)-1])
                    this.mutPlaceList.remove(this.cn)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_aus.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.aus !in this.mutPlaceList){
                    this.mutPlaceList.add(this.aus)
                    this.placeList.add(Place(aus,0, 0,10))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.aus in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.aus)-1])
                    this.mutPlaceList.remove(this.aus)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }

        cb_af.setOnCheckedChangeListener{ buttonView, ischecked ->
            if(ischecked){
                if(this.af !in this.mutPlaceList){
                    this.mutPlaceList.add(this.af)
                    this.placeList.add(Place(af,0, 0,25))
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }else if(!ischecked){
                if(this.af in this.mutPlaceList){
                    this.placeList.remove(placeList[mutPlaceList.indexOf(this.af)-1])
                    this.mutPlaceList.remove(this.af)
                    arrayUpdate()
                    //Toast.makeText(this,"目前已選擇:$placeList", Toast.LENGTH_SHORT).show()
                    Log.e("已選", placeList.toString())
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    fun arrayUpdate(){
        var expect = 1
        var score = 120
        for(i in this.placeList){
            i.rank = expect
            i.score = score
            expect++
            score--
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun calculate(){
        var sum = 0
        var the2dArray: Array<Array<Int>> = Array(placeList.size+1, { Array(day+1, { 0 }) })
        var schedule: ArrayList<Place>
        for (i in 1 until placeList.size+1){
            for(j in 1 until day+1){
                //Log.e("j",j.toString())
                the2dArray[i][j] = the2dArray[i-1][j]
                if(j >= placeList[i-1].consumeDay){
                    the2dArray[i][j] = max(the2dArray[i-1][j-placeList[i-1].consumeDay] + placeList[i-1].score,the2dArray[i-1][j])
                }
            }
        }
        //Log.e("bag",the2dArray.toString())

        schedule = track(the2dArray,this.day,placeList)
        Log.e("result", schedule.toString())
        var b = Bundle()
        var intent = Intent(this,SecondActivity::class.java)
        Log.e("day",this.day.toString())
        b.putParcelableArrayList("schedule",schedule)
        b.putString("day",this.day.toString())
        schedule.forEach { sum+=it.consumeDay }
        b.putString("sum",sum.toString())
        intent.putExtra("bundle",b)
        try{
            finish()
            startActivity(intent)
        }catch(e:Exception){
            Log.e("e",e.toString())
        }



    }
    fun track(the2dArray: Array<Array<Int>>,day: Int,placeList: ArrayList<Place>): ArrayList<Place> {
        var schedule: ArrayList<Place> = ArrayList<Place>()
        var day = this.day
        for (i in placeList.size downTo 2){
            if(the2dArray[i][day] != the2dArray[i-1][day]){
                schedule.add(placeList[i-1])
                day -= placeList[i-1].consumeDay
            }
        }
        if(the2dArray[1][day] > 0){
            schedule.add(placeList[0])
        }
        return schedule
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        System.exit(0)
    }
}

@Parcelize
data class Place(
    var place: String,
    var rank: Int,
    var score: Int,
    var consumeDay: Int
) : Parcelable