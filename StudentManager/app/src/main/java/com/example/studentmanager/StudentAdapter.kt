package com.example.studentmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val context: Context,
    private var studentList: List<Student>,
    private val onItemClickListener: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private val selectedStudents = mutableSetOf<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentList[position]
        holder.studentIdTextView.text = student.studentId
        holder.fullNameTextView.text = student.fullName

        holder.checkbox.isChecked = selectedStudents.contains(student)

        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedStudents.add(student)
            } else {
                selectedStudents.remove(student)
            }
        }

        holder.itemView.setOnClickListener { onItemClickListener(student) }
    }

    override fun getItemCount(): Int = studentList.size

    fun setStudentList(studentList: List<Student>) {
        this.studentList = studentList
        notifyDataSetChanged()
    }

    fun getSelectedStudents(): List<Student> = selectedStudents.toList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentIdTextView: TextView = itemView.findViewById(R.id.text_student_id)
        val fullNameTextView: TextView = itemView.findViewById(R.id.text_full_name)
        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
    }
}

