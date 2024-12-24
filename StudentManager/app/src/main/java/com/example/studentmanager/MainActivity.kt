package com.example.studentmanager

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private var studentList = mutableListOf<Student>()
    private lateinit var appDatabase: AppDatabase
    private lateinit var searchEditText: EditText

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        searchEditText = findViewById(R.id.edit_search)
        appDatabase = AppDatabase.getInstance(this)

        adapter = StudentAdapter(this, studentList) { student ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("studentId", student.id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        activityScope.launch {
            withContext(Dispatchers.IO) {
                    addSampleData()
            }
            loadStudents()
        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                searchStudents(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_delete -> {
                deleteSelectedStudents()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun deleteSelectedStudents() {
        CoroutineScope(Dispatchers.Main).launch {
            val selectedStudents = adapter.getSelectedStudents()
            if (selectedStudents.isNotEmpty()) {
                withContext(Dispatchers.IO) {
                    appDatabase.studentDao().deleteStudents(selectedStudents)
                }
                loadStudents()
            } else {
                Toast.makeText(this@MainActivity, "No student was selected!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudents()
    }

    private fun loadStudents() {
        activityScope.launch {
            val students = withContext(Dispatchers.IO) {
                appDatabase.studentDao().getAllStudents()
            }
            studentList.clear()
            studentList.addAll(students)
            adapter.notifyDataSetChanged()
        }
    }

    private fun searchStudents(keyword: String) {
        activityScope.launch {
            val students = withContext(Dispatchers.IO) {
                appDatabase.studentDao().searchStudents("%$keyword%")
            }
            studentList.clear()
            studentList.addAll(students)
            adapter.notifyDataSetChanged()
        }
    }

    private fun addSampleData() {
        appDatabase.studentDao().deleteAllStudents()
        val sampleStudents = listOf(
            Student(studentId = "SV001", fullName = "Nguyễn Văn An", dateOfBirth = "2000-01-01", email = "an.nv@example.com"),
            Student(studentId = "SV002", fullName = "Trần Thị Bảo", dateOfBirth = "2000-02-02", email = "bao.tt@example.com"),
            Student(studentId = "SV003", fullName = "Lê Hoàng Cường", dateOfBirth = "2000-03-03", email = "cuong.lh@example.com"),
            Student(studentId = "SV004", fullName = "Phạm Thị Dung", dateOfBirth = "2000-04-04", email = "dung.pt@example.com"),
            Student(studentId = "SV005", fullName = "Đỗ Minh Đức", dateOfBirth = "2000-05-05", email = "duc.dm@example.com"),
            Student(studentId = "SV006", fullName = "Vũ Thị Hoa", dateOfBirth = "2000-06-06", email = "hoa.vt@example.com"),
            Student(studentId = "SV007", fullName = "Hoàng Văn Hải", dateOfBirth = "2000-07-07", email = "hai.hv@example.com"),
            Student(studentId = "SV008", fullName = "Bùi Thị Hạnh", dateOfBirth = "2000-08-08", email = "hanh.bt@example.com"),
            Student(studentId = "SV009", fullName = "Đinh Văn Hùng", dateOfBirth = "2000-09-09", email = "hung.dv@example.com"),
            Student(studentId = "SV010", fullName = "Nguyễn Thị Linh", dateOfBirth = "2000-10-10", email = "linh.nt@example.com"),
            Student(studentId = "SV011", fullName = "Phạm Văn Long", dateOfBirth = "2000-11-11", email = "long.pv@example.com"),
            Student(studentId = "SV012", fullName = "Trần Thị Mai", dateOfBirth = "2000-12-12", email = "mai.tt@example.com"),
            Student(studentId = "SV013", fullName = "Lê Thị Ngọc", dateOfBirth = "2000-01-02", email = "ngoc.lt@example.com"),
            Student(studentId = "SV014", fullName = "Vũ Văn Nam", dateOfBirth = "2000-02-03", email = "nam.vv@example.com"),
            Student(studentId = "SV015", fullName = "Hoàng Thị Phương", dateOfBirth = "2000-03-04", email = "phuong.ht@example.com"),
            Student(studentId = "SV016", fullName = "Đỗ Văn Quân", dateOfBirth = "2000-04-05", email = "quan.dv@example.com"),
            Student(studentId = "SV017", fullName = "Nguyễn Thị Thu", dateOfBirth = "2000-05-06", email = "thu.nt@example.com"),
            Student(studentId = "SV018", fullName = "Trần Văn Tài", dateOfBirth = "2000-06-07", email = "tai.tv@example.com"),
            Student(studentId = "SV019", fullName = "Phạm Thị Tuyết", dateOfBirth = "2000-07-08", email = "tuyet.pt@example.com"),
            Student(studentId = "SV020", fullName = "Lê Văn Vũ", dateOfBirth = "2000-09-10", email = "vu.lv@example.com"),
        )
        appDatabase.studentDao().insertAll(sampleStudents)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}
