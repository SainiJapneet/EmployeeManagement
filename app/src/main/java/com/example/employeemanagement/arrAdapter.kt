package com.example.employeemanagement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ViewHolder(v: View){
    val txtName: TextView = v.findViewById(R.id.txtName)
    val txtID: TextView = v.findViewById(R.id.txtID)
}

class arrAdapter(var ctx: Context, var xmlAddress:Int , var arrList: ArrayList<Model>):ArrayAdapter<Model>(ctx,xmlAddress,arrList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View
        var viewHolder: ViewHolder

        if(convertView == null){
            view = layoutInflater.inflate(xmlAddress,null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder//It is responsible for handling the parameters of viewHolder class.
        }
        else{
            view = convertView//The view which is getting removed from screen is getting stored in the view
            viewHolder = view.tag as ViewHolder//type casting as ViewHolder object
        }

//        val txtName: TextView = view.findViewById(R.id.txtName)
//        val txtID: TextView = view.findViewById(R.id.txtID)
        viewHolder.txtName.text = arrList[position].name
        viewHolder.txtID.text = arrList[position].empID.toString()

        return view
    }
}