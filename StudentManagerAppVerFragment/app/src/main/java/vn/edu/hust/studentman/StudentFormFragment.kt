package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class StudentFormFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_form, container, false)
        return view
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_edit_student)
//
//        val editTextName = findViewById<EditText>(R.id.edit_text_name)
//        val editTextId = findViewById<EditText>(R.id.edit_text_id)
//
//        editTextName.setText(intent.getStringExtra("studentName"))
//        editTextId.setText(intent.getStringExtra("studentId"))
//
//        setResult(Activity.RESULT_CANCELED)
//
//        findViewById<Button>(R.id.button_add).setOnClickListener {
//            if (editTextName.text == null || editTextName.text.isBlank()) {
//                Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if (editTextId.text == null || editTextId.text.isBlank()) {
//                Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            intent.putExtra("studentName", editTextName.text.toString())
//            intent.putExtra("studentId", editTextId.text.toString())
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//
//        findViewById<Button>(R.id.button_cancel).setOnClickListener {
//            setResult(RESULT_CANCELED)
//            finish()
//        }
//    }
}