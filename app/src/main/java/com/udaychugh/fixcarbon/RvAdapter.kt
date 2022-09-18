package com.udaychugh.fixcarbon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter (val userList: ArrayList<Model>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_item_layout, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.sp?.text = userList[p1].sp.toString()
        p0.ht?.text = userList[p1].ht.toString()
        p0.pd?.text = userList[p1].pd.toString()
        p0.dm?.text = userList[p1].dm.toString()
        p0.gl?.text = userList[p1].gl.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val sp = itemView.findViewById<TextView>(R.id.nameofscpeices)
        val ht = itemView.findViewById<TextView>(R.id.heightoftree)
        val pd = itemView.findViewById<TextView>(R.id.plantationdate)
        val dm = itemView.findViewById<TextView>(R.id.diameteroftree)
        val gl = itemView.findViewById<TextView>(R.id.geolocationoftree)
    }
}