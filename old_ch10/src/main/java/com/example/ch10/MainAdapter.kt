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
        return MainViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        //item외에 표시돼는 데이터를 세팅한다
        holder.binding.tvDate.text = mainDataList[position].date
        holder.binding.tvMessage.text = mainDataList[position].message
        holder.binding.tvName.text = mainDataList[position].name

    }


    override fun getItemCount(): Int {
        return mainDataList.size
    }
}
class MainViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)