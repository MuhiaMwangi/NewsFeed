package com.mm.bud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NewsFeedActivityRepository {

    //getting a reference to the db which this project is connected to inside firebase
    private val database = Firebase.database
    private val newsFeedReference = database.getReference("news_feed")
    fun fetchNewsFeed(liveData: MutableLiveData<List<NewsFeedItem>>) {
        newsFeedReference.orderByChild("_rank")

        //Listening for any changes that happen to news_feed node
        //Reading from the db
        newsFeedReference.addValueEventListener(object : ValueEventListener {
            //this method is called once with the initial value and again whenever data at this location is updated
            override fun onDataChange(snapshot: DataSnapshot) {
                val newsFeedItems: List<NewsFeedItem> = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(NewsFeedItem::class.java)!!
                }
                liveData.postValue(newsFeedItems)
                  // Log.i("DATA ITEMS", newsFeedItems.toString())
            }
                 //failed to read value
            override fun onCancelled(error: DatabaseError) {
                //nothing to do
            }
        })
    }
}
