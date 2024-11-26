package vn.edu.hust.studentman

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_student)

        val editTextName = findViewById<EditText>(R.id.edit_text_name)
        val editTextId = findViewById<EditText>(R.id.edit_text_id)

        editTextName.setText(intent.getStringExtra("studentName"))
        editTextId.setText(intent.getStringExtra("studentId"))

        setResult(Activity.RESULT_CANCELED)

        findViewById<Button>(R.id.button_add).setOnClickListener {
            if (editTextName.text == null || editTextName.text.isBlank()) {
                Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (editTextId.text == null || editTextId.text.isBlank()) {
                Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            intent.putExtra("studentName", editTextName.text)
            intent.putExtra("studentId", editTextId.text)
            setResult(RESULT_OK)
            finish()
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}