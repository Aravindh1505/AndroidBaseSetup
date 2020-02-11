package com.aravindh.andriodbasesetup.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.aravindh.andriodbasesetup.database.MyDatabase
import com.aravindh.andriodbasesetup.database.entities.Department
import com.aravindh.andriodbasesetup.database.entities.Student
import com.aravindh.andriodbasesetup.databinding.FragmentStudentBinding
import com.aravindh.andriodbasesetup.utils.DEPARTMENT
import com.aravindh.andriodbasesetup.utils.Logger

/**
 * A simple [Fragment] subclass.
 */
class StudentFragment : Fragment() {

    private lateinit var database: MyDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStudentBinding =
            FragmentStudentBinding.inflate(inflater, container, false)

        activity?.let {
            database = MyDatabase.getInstance(it)
        }

        addDepartment()
        addStudent()

        getStudents()


        return binding.root
    }

    private fun addDepartment() {
        val departmentList = arrayListOf<Department>()

        val departmentTamil = Department(DEPARTMENT.TAMIL.departmentId, DEPARTMENT.TAMIL.toString())
        val departmentEnglish =
            Department(DEPARTMENT.ENGLISH.departmentId, DEPARTMENT.ENGLISH.toString())
        val departmentMaths = Department(DEPARTMENT.MATHS.departmentId, DEPARTMENT.MATHS.toString())

        departmentList.add(departmentTamil)
        departmentList.add(departmentEnglish)
        departmentList.add(departmentMaths)

        database.departmentDao.insert(departmentList)
    }

    private fun addStudent() {
        val studentList = arrayListOf<Student>()

        val student1 = Student(
            1,
            "Aravindh S",
            "9791779068",
            "aravindh@gmail.com",
            "Test123@",
            DEPARTMENT.TAMIL.departmentId
        )

        val student2 =
            Student(
                2,
                "Vijay",
                "9791779066",
                "vijay@gmail.com",
                "Test@123",
                DEPARTMENT.MATHS.departmentId
            )

        studentList.add(student1)
        studentList.add(student2)

        database.studentDao.insert(studentList)
    }

    fun getStudents() {
        database.studentDao.getStudentsBasedOnDepartment().observe(this, Observer {
            Logger.d("list size : ${it.size}")

            for (student in it) {
                Logger.d("studentId : ${student.studentId}")
                Logger.d("studentName : ${student.studentName}")
                Logger.d("departmentName : ${student.departmentName}")
            }
        })
    }


}
