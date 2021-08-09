package com.example.loginsighninvalidation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(private  val ProuductList: List<ProuductList>):RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>(){
    class  ExampleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val  imageView:ImageView = itemView.findViewById(R.id.image_view)
        val textView1:TextView = itemView.findViewById(R.id.text_view_1)
        val textView2:TextView = itemView.findViewById(R.id.text_view_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_list,
        parent,false)
        return  ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {


        val currentItem=ProuductList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text=currentItem.text1
        holder.textView1.text=currentItem.text2




    }

    override fun getItemCount() = ProuductList.size


}