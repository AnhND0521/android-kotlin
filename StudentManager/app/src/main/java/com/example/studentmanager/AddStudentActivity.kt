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

class AddStudentActivity : AppCompatActivity() {

    private lateinit var studentIdEditText: EditText
    private lateinit var fullNameEditText: EditText
    private lateinit var dateOfBirthEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var addButton: Button
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        studentIdEditText = findViewById(R.id.edit_student_id)
        fullNameEditText = findViewById(R.id.edit_full_name)
        dateOfBirthEditText = findViewById(R.id.edit_birth_of_date)
        emailEditText = findViewById(R.id.edit_email)
        addButton = findViewById(R.id.btn_add)

        appDatabase = AppDatabase.getInstance(this)

        addButton.setOnClickListener {
            val studentId = studentIdEditText.text.toString()
            val fullName = fullNameEditText.text.toString()
            val dateOfBirth = dateOfBirthEditText.text.toString()
            val email = emailEditText.text.toString()

            if (studentId.isNotBlank() && fullName.isNotBlank() && dateOfBirth.isNotBlank() && email.isNotBlank()) {
                val student = Student(studentId = studentId, fullName = fullName, dateOfBirth = dateOfBirth, email = email)

                CoroutineScope(Dispatchers.IO).launch {
                    appDatabase.studentDao().insert(student)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddStudentActivity, "Added student successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter all required fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
