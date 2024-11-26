package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
  )
  private lateinit var studentAdapter: StudentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(this, students)

    val listView: ListView = findViewById<ListView>(R.id.list_view_students)
    listView.adapter = studentAdapter
    registerForContextMenu(listView)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_add_new -> {
        val intent = Intent(this, AddEditStudentActivity::class.java)
        val launcher = registerForActivityResult(
          ActivityResultContracts.StartActivityForResult()
        ) { it: ActivityResult ->
          if (it.resultCode == RESULT_OK) {
            val studentName = it.data!!.getStringExtra("studentName")!!
            val studentId = it.data!!.getStringExtra("studentId")!!
            students.add(StudentModel(studentName, studentId))
            studentAdapter.notifyDataSetChanged()
          }
        }
        launcher.launch(intent)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateContextMenu(
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenu.ContextMenuInfo?
  ) {
    menuInflater.inflate(R.menu.context, menu)
    super.onCreateContextMenu(menu, v, menuInfo)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
    when (item.itemId) {
      R.id.action_edit -> {
        val intent = Intent(this, AddEditStudentActivity::class.java)
        intent.putExtra("studentName", students[pos].studentName)
        intent.putExtra("studentId", students[pos].studentId)
        val launcher = registerForActivityResult(
          ActivityResultContracts.StartActivityForResult()
        ) { it: ActivityResult ->
          if (it.resultCode == RESULT_OK) {
            val studentName = it.data!!.getStringExtra("studentName")!!
            val studentId = it.data!!.getStringExtra("studentId")!!
            students[pos] = StudentModel(studentName, studentId)
            studentAdapter.notifyDataSetChanged()
          }
        }
        launcher.launch(intent)
      }
      R.id.action_remove -> {
        students.removeAt(pos)
        studentAdapter.notifyDataSetChanged()
      }
    }
    return super.onContextItemSelected(item)
  }
}
