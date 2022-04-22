package com.mm.bud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mm.bud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DataBinding
        val binding:ActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //ViewModel
        val viewModel:NewsFeedViewModel=ViewModelProvider(this).get(NewsFeedViewModel::class.java)
        binding.lifecycleOwner= this
        binding.viewModel=viewModel

        //Calling fetchNewsFeed method in viewModel which in turn calls fetchNewsFeed which is in the repository which in turn fetches data from Firebase realtime db
        //viewModel.fetchNewsFeed()

        //Creating an instance of recyclerView Adapter
        val newsFeedAdapter=NewFeedRVAdapter()

        //Setting the Adapter to our recyclerView
        binding.recyclerView.adapter=newsFeedAdapter

        //Calling fetchNewsFeed method in viewModel which in turn calls fetchNewsFeed which is in the repository which in turn fetches data from Firebase realtime db
        viewModel.fetchNewsFeed()

      //  newsFeedAdapter.setItems(
        //: we need  list of items which will come from the viewmodel--
        // hence we are going to observe newsFeedLiveData as shown below)

        viewModel.newsFeedLiveData.observe(this){
                newsFeedItems->newsFeedAdapter.setItems(newsFeedItems)
        }
        }
    }

//setItems method in Adapter class
/*
 fun setItems(newsFeedItems: List<NewsFeedItem>) {
 NewsFeedAdapter.newsFeedItems.clear()
 NewsFeedAdapter.newsFeedItems.addAll(newsFeedItems)
 notifyDataSetChanged()
}*/