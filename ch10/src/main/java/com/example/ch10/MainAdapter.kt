package com.example.ch10

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch10.databinding.ItemMainBinding
import com.example.ch10.MainData
import com.example.ch10.MainViewHolder
import android.view.LayoutInflater

class MainAdapter(val mainDataList: MutableList<MainData>): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(itemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder: MainViewHolder,
        position: Int
        ){
            holder.binding.tnname.text = mainDataList.get(position).name
            holder.binding.tvmessage.text = mainDataList.get(position).message
            holder.binding.tvdate.text = mainDataList.get(position).date
        }
    }


    override fun getItemCount(): Int {
        return mainDataList.size
    }
}
class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)