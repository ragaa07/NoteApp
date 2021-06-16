package com.example.noteapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import entities.Notes
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter(val arrayList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvTitle.text = arrayList[position].title
        holder.itemView.tvDesc.text = arrayList[position].noteText
        holder.itemView.tvDateTime.text = arrayList[position].dateTime
        if(arrayList[position].color!=null){
            holder.itemView.linearCard.setBackgroundColor(Color.parseColor(arrayList[position].color))
        }else
        {

        }


    }
}