package vn.edu.hust.studentman

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var studentName: String? = null
        var studentId: String? = null
        var pos: Int = -1

        arguments?.let {
            studentName = it.getString("studentName", "")
            studentId = it.getString("studentId", "")
            pos = it.getInt("position", -1)
        }

        if (studentName != null && studentId != null) {
            val student = StudentModel(studentName!!, studentId!!)
            if (pos >= 0) {
                students[pos] = student
            } else {
                students.add(student)
            }
        }

        studentAdapter = StudentAdapter(requireContext(), students)

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        val listView = view.findViewById<ListView>(R.id.list_view_students)
        listView.adapter = studentAdapter
        registerForContextMenu(listView)

        return view
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            R.id.action_edit -> {
                val args = Bundle()
                args.putString("studentName", students[pos].studentName)
                args.putString("studentId", students[pos].studentId)
                args.putInt("position", pos)
                findNavController().navigate(
                    R.id.action_studentListFragment_to_studentFormFragment,
                    args
                )
            }

            R.id.action_remove -> {
                students.removeAt(pos)
                studentAdapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }

    companion object {
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

//        @JvmStatic
//        fun newInstance(studentName: String, studentId: String, pos: Int = -1): StudentListFragment {
//            val student = StudentModel(studentName, studentId)
//            if (pos >= 0) {
//                students[pos] = student
//            } else {
//                students.add(student)
//            }
//
//            val instance = StudentListFragment()
//            instance.apply {
//                studentList = students
//            }
//            return instance
//        }
    }
}