package com.example.studentlistapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlistapp.ui.theme.StudentListAppTheme

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách sinh viên
        val studentList = listOf(
            Student("Nguyễn Văn An", "20210001"),
            Student("Trần Thị Bích", "20210002"),
            Student("Lê Văn Cường", "20220003"),
            Student("Phạm Thị Dung", "20210004"),
            Student("Vũ Văn Hùng", "20230005"),
            Student("Nguyễn Thị Mai", "20210006"),
            Student("Đinh Công Sơn", "20220007"),
            Student("Lý Thị Thảo", "20220008"),
            Student("Bùi Văn Tân", "20230009"),
            Student("Phan Thị Lan", "20210010"),
            Student("Đoàn Minh Quang", "20220011"),
            Student("Nguyễn Thị Ngọc", "20220012"),
            Student("Đặng Văn Nam", "20230013"),
            Student("Trương Thị Thanh", "20220014"),
            Student("Nguyễn Văn Long", "20210015"),
            Student("Lê Thị Thùy", "20230016"),
            Student("Ngô Văn Lâm", "20230017"),
            Student("Hoàng Minh Tuấn", "20220018"),
            Student("Phạm Thị Vân", "20210019"),
            Student("Vương Thị Hồng", "20230020")
        )


        // Khởi tạo Adapter
        studentAdapter = StudentAdapter(studentList)

        // Gán Adapter cho RecyclerView
        val rvStudents = findViewById<RecyclerView>(R.id.rvStudents)
        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = studentAdapter

        // Tìm kiếm
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(keyword, ignoreCase = true) ||
                                it.studentId.contains(keyword, ignoreCase = true)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
