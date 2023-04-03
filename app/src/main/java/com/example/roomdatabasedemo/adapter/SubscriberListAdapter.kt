package com.example.roomdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.roomdb.Subscriber

class SubscriberListAdapter(
private val clickListener: (Subscriber)-> Unit): RecyclerView.Adapter<ViewHolder>() {
    private val mList = ArrayList<Subscriber>()
    fun setList(subscribers: List<Subscriber>) {
        mList.clear()
        mList.addAll(subscribers)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_subscriber_item,
            parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position], clickListener)
    }
    override fun getItemCount(): Int {
        return mList.size
    }



}
class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    private val nameTV: TextView = itemView.findViewById(R.id.tv_subscriber_item_name)
    private val emailTV: TextView = itemView.findViewById(R.id.tv_subscriber_item_email)
    private val layoutSubscriberList: ConstraintLayout = itemView.findViewById(R.id.layout_subscriber_list)
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
       nameTV.text = subscriber.name
       emailTV.text = subscriber.email
        layoutSubscriberList.setOnClickListener{
            clickListener(subscriber)
        }
    }
}