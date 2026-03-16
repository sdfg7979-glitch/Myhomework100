package com.example.ch11 // 반드시 본인의 패키지 이름과 일치해야 합니다.

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11.databinding.ItemTodoBinding

class TodoAdapter(
    private val items: List<Todo>,
    private val onDeleteClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // 각 아이템 뷰를 잡고 있는 홀더
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // item_todo.xml 레이아웃을 가져와서 바인딩합니다.
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvContent.text = item.content // 할 일 내용 세팅

        // 삭제 버튼 클릭 시 처리
        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}