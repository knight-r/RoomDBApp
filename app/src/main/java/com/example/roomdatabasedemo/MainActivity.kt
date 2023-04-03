package com.example.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasedemo.adapter.SubscriberListAdapter
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import com.example.roomdatabasedemo.roomdb.Subscriber
import com.example.roomdatabasedemo.roomdb.SubscriberDatabase
import com.example.roomdatabasedemo.roomdb.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var subscriberViewModel : SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this , factory)[SubscriberViewModel::class.java]
        mainBinding.myViewModel = subscriberViewModel
        mainBinding.lifecycleOwner = this
        setSubscriberListToRecyclerView()
    }

    private fun setSubscriberListToRecyclerView() {
        mainBinding.rvSubscriberList.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag", it.toString())
           mainBinding.rvSubscriberList.adapter = SubscriberListAdapter(it) { selectedItem: Subscriber ->
               subscriberItemClicked(
                   selectedItem
               )
           }
        })
    }

    private fun subscriberItemClicked(subscriber: Subscriber) {
        Toast.makeText(this, "selected item name is: ${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscriberViewModel.initUpdateOrDelete(subscriber)
    }
}