package com.karamlyy.mytodo2

import android.R.layout.simple_list_item_1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView

    private var tasks = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTask = findViewById(R.id.et_task)
        btnAdd = findViewById(R.id.btn_add)
        listView = findViewById(R.id.list_view)

        adapter = ArrayAdapter(this, simple_list_item_1, tasks)
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val task = etTask.text.toString().trim()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                etTask.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_LONG).show()
            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val task = tasks[position]
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Task deleted: $task", Toast.LENGTH_LONG).show()
        }
    }
}
