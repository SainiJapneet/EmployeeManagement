package com.example.employeemanagement

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddEmployee : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance() //tells where is our database located
    val refEmp: DatabaseReference = database.reference.child("EmployeeManagement")//creates a table using function child

    //Widgets declaration
    lateinit var edtName: EditText
    lateinit var edtID: EditText
    lateinit var edtPosition: EditText
    lateinit var edtDOB: EditText
    lateinit var edtSalary: EditText
    lateinit var edtPhone: EditText
    lateinit var edtEmail: EditText
    lateinit var edtAddress: EditText
    lateinit var btnAdd: Button

    //variables declaration
    lateinit var name: String
    var ID: Int = 0
    lateinit var position: String
    lateinit var dob: String
    var salary: Int = 0
    var phone: Long = 0
    lateinit var email: String
    lateinit var address: String
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        edtName = findViewById(R.id.edtName)
        edtID = findViewById(R.id.edtID)
        edtPosition = findViewById(R.id.edtPosition)
        edtDOB = findViewById(R.id.edtDOB)
        edtSalary = findViewById(R.id.edtSalary)
        edtPhone = findViewById(R.id.edtPhone)
        edtEmail = findViewById(R.id.edtEmail)
        edtAddress = findViewById(R.id.edtAddress)
        btnAdd = findViewById(R.id.btnAdd)


        btnAdd.setOnClickListener {
            var intent = Intent(this@AddEmployee, EmployeeList::class.java)

            name = edtName.text.toString()
            ID = edtID.text.toString().toInt()
            position = edtPosition.text.toString()
            dob = edtDOB.text.toString()
            salary = edtSalary.text.toString().toInt()
            phone = edtPhone.text.toString().toLong()
            email = edtEmail.text.toString()
            address = edtAddress.text.toString()


            edtName.setText("")
            edtID.setText("")
            edtPosition.setText("")
            edtDOB.setText("")
            edtSalary.setText("")
            edtPhone.setText("")
            edtEmail.setText("")
            edtAddress.setText("")


            //------------------Insert data in firebase-------------------------------------
            //We use NOSQL(Not Only SQL) in Android app development instead of RDBMS
            //push() is responsible for creating the key and key ensures that it(token) is unique
            val token: String =
                refEmp.push().key.toString()//used to generate unique token for every instance in database
            val emp = Model(token, name, ID, position, dob, salary, phone, email, address)
            //Insertion was in context to individual record/child
            refEmp.child(token).setValue(emp)
                .addOnCompleteListener { status -> //it is a parameter which will hold acknowledgement whether insertion in database is successful
                    if (status.isSuccessful) {
                        Toast.makeText(
                            this@AddEmployee,
                            "Employee added successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(this@AddEmployee, "Operation failed!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            startActivity(intent)
        }
    }

}