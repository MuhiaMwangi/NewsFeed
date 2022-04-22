package com.mm.bud

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mm.bud.databinding.ViewholderNewsFeedItemBinding

class NewFeedRVAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //List from firebase
    private val newsFeedItems = mutableListOf<NewsFeedItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsFeedItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsFeedItemViewHolder).onBind(newsFeedItems[position])
    }

    override fun getItemCount() = newsFeedItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newsFeedItems: List<NewsFeedItem>) {
        this.newsFeedItems.clear()
        this.newsFeedItems.addAll(newsFeedItems)
        notifyDataSetChanged()
    }

    inner class NewsFeedItemViewHolder(parent: ViewGroup ):RecyclerView.ViewHolder(
        //Layout Inflater knows how to inflate an XML Layout into the hierarcy of view Objects
        LayoutInflater.from(parent.context).inflate(R.layout.viewholder_news_feed_item, parent,false)
    ){
        private val binding=ViewholderNewsFeedItemBinding.bind(itemView)

        fun onBind(newsFeedItem: NewsFeedItem){
            binding.titleTextView.text= newsFeedItem.title
            binding.descriptionTextView.text= newsFeedItem.description
        }
    }
}
