package com.mm.bud

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class NewsFeedViewModel: ViewModel() {
    private val repository= NewsFeedActivityRepository()

    private val _newsFeedLiveData=MutableLiveData<List<NewsFeedItem>>()
    val newsFeedLiveData: LiveData<List<NewsFeedItem>> = _newsFeedLiveData

    fun fetchNewsFeed(){
        repository.fetchNewsFeed(_newsFeedLiveData)
    }
}
