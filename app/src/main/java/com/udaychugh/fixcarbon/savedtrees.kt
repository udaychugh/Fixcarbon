package com.udaychugh.fixcarbon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class savedtrees : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savedtrees)

        val recyclerView = findViewById<RecyclerView>(R.id.rvlist)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val db = DBHelper(this, null)
        val cursor = db.getName()


        var sps: String
        var hgt: String
        var pod: String
        var dmr: String
        //var gel: String

        cursor!!.moveToFirst()
        sps = cursor.getString(cursor.getColumnIndex(DBHelper.SPECIES_COL)) + "\n"
        hgt = cursor.getString(cursor.getColumnIndex(DBHelper.HEIGHT_COL)) + "\n"
        pod = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL)) + "\n"
        dmr = cursor.getString(cursor.getColumnIndex(DBHelper.DIAMETER_COL)) + "\n"
        //gel = cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_COL)) + "\n"

        val dataList = ArrayList<Model>()
        dataList.add(Model(sps, hgt, pod, dmr, sps))
        while (cursor.moveToNext())
        {
            sps = cursor.getString(cursor.getColumnIndex(DBHelper.SPECIES_COL)) + "\n"
            hgt = cursor.getString(cursor.getColumnIndex(DBHelper.HEIGHT_COL)) + "\n"
            pod = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL)) + "\n"
            dmr = cursor.getString(cursor.getColumnIndex(DBHelper.DIAMETER_COL)) + "\n"
            //gel = cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_COL)) + "\n"

            dataList.add(Model(sps, hgt, pod, dmr, sps))





        }

        cursor.close()



        val rvAdapter = RvAdapter(dataList)
        recyclerView.adapter = rvAdapter
    }
}