package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

//    studentAdapter = StudentAdapter(this, students)
//
//    val listView: ListView = findViewById<ListView>(R.id.list_view_students)
//    listView.adapter = studentAdapter
//    registerForContextMenu(listView)
//
//    addNewLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//      if (result.resultCode == RESULT_OK) {
//        val studentName = result.data!!.getStringExtra("studentName")!!
//        val studentId = result.data!!.getStringExtra("studentId")!!
//        students.add(StudentModel(studentName, studentId))
//        studentAdapter.notifyDataSetChanged()
//      }
//    }
//
//    editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//      if (result.resultCode == RESULT_OK) {
//        val studentName = result.data!!.getStringExtra("studentName")!!
//        val studentId = result.data!!.getStringExtra("studentId")!!
//        val pos = result.data!!.getIntExtra("position", -1)
//        if (pos != -1) {
//          students[pos] = StudentModel(studentName, studentId)
//          studentAdapter.notifyDataSetChanged()
//        }
//      }
//    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    when (item.itemId) {
//      R.id.action_add_new -> {
//        val intent = Intent(this, AddEditStudentActivity::class.java)
//        addNewLauncher.launch(intent)
//      }
//    }
    return super.onOptionsItemSelected(item)
  }
}
