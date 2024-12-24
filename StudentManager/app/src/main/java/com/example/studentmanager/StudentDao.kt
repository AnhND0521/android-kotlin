package com.example.studentmanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    fun insert(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(students: List<Student>)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): Student?

    @Query("SELECT * FROM students WHERE student_id LIKE :keyword OR full_name LIKE :keyword")
    fun searchStudents(keyword: String): List<Student>

    @Query("DELETE FROM students WHERE id = :id")
    fun deleteById(id: Int)

    @Delete
    fun deleteStudents(students: List<Student>)

    @Query("DELETE FROM students WHERE 1 = 1")
    fun deleteAllStudents();
}


