package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var studentIdEditText: EditText
    private lateinit var fullNameEditText: EditText
    private lateinit var dateOfBirthEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var appDatabase: AppDatabase
    private var studentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        studentIdEditText = findViewById(R.id.edit_student_id)
        fullNameEditText = findViewById(R.id.edit_full_name)
        dateOfBirthEditText = findViewById(R.id.edit_birth_of_date)
        emailEditText = findViewById(R.id.edit_email)
        updateButton = findViewById(R.id.btn_update)
        deleteButton = findViewById(R.id.btn_delete)
        appDatabase = AppDatabase.getInstance(this)

        studentId = intent.getIntExtra("studentId", -1)

        loadStudentDetails()

        updateButton.setOnClickListener {
            updateStudent()
        }

        deleteButton.setOnClickListener {
            deleteStudent()
        }
    }

    private fun loadStudentDetails() {
        CoroutineScope(Dispatchers.Main).launch {
            val student = withContext(Dispatchers.IO) {
                appDatabase.studentDao().getStudentById(studentId)
            }
            if (student != null) {
                studentIdEditText.setText(student.studentId)
                fullNameEditText.setText(student.fullName)
                dateOfBirthEditText.setText(student.dateOfBirth)
                emailEditText.setText(student.email)
            } else {
                Toast.makeText(this@DetailActivity, "No student was found!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun updateStudent() {
        val studentId = studentIdEditText.text.toString()
        val fullName = fullNameEditText.text.toString()
        val dateOfBirth = dateOfBirthEditText.text.toString()
        val email = emailEditText.text.toString()

        if (studentId.isNotBlank() && fullName.isNotBlank() && dateOfBirth.isNotBlank() && email.isNotBlank()) {
            CoroutineScope(Dispatchers.IO).launch {
                val student = Student(
                    id = this@DetailActivity.studentId,
                    studentId = studentId,
                    fullName = fullName,
                    dateOfBirth = dateOfBirth,
                    email = email
                )
                appDatabase.studentDao().update(student)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DetailActivity, "Updated successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Please enter all required fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteStudent() {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.studentDao().deleteById(studentId)

            withContext(Dispatchers.Main) {
                Toast.makeText(this@DetailActivity, "Deleted successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
