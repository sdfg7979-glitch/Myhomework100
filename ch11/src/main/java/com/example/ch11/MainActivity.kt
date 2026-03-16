package com.example.ch11 // 패키지명을 ch11로 수정

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch11.databinding.ActivityMainBinding

data class Todo(val id: Long, val content: String)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todoList = mutableListOf<Todo>()
    private lateinit var todoAdapter: TodoAdapter
    private var count = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnAdd.setOnClickListener {
            val content = binding.editTodo.text.toString()
            if (content.isNotEmpty()) {
                val newTodo = Todo(count++, content)
                todoList.add(newTodo)
                todoAdapter.notifyDataSetChanged()
                binding.editTodo.text.clear()
            } else {
                Toast.makeText(this, "할 일을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(todoList) { todo ->
            todoList.remove(todo)
            todoAdapter.notifyDataSetChanged()
        }
        binding.recyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}