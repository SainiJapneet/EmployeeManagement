package com.example.employeemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class editEmployee : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance() //tells where is our database located
    val refEmp: DatabaseReference = database.reference.child("EmployeeManagement")//creates a table using function child

    lateinit var edtEditName: EditText
    lateinit var edtEditID: EditText
    lateinit var edtEditPosition: EditText
    lateinit var edtEditDate: EditText
    lateinit var edtEditSalary: EditText
    lateinit var edtEditPhone: EditText
    lateinit var edtEditEmail: EditText
    lateinit var edtEditAddress: EditText
    lateinit var btnUpdate: Button

    lateinit var id: String
    lateinit var name: String
    var empID: Int=0
    lateinit var pos: String
    lateinit var dob: String
    var salary: Int=0
    var phone: Long=0
    lateinit var email: String
    lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_employee)
        edtEditName = findViewById(R.id.edtEditName)
        edtEditID = findViewById(R.id.edtEditID)
        edtEditPosition = findViewById(R.id.edtEditPosition)
        edtEditDate = findViewById(R.id.edtEditDate)
        edtEditSalary = findViewById(R.id.edtEditSalary)
        edtEditPhone = findViewById(R.id.edtEditPhone)
        edtEditEmail = findViewById(R.id.edtEditEmail)
        edtEditAddress = findViewById(R.id.edtEditAddress)
        btnUpdate = findViewById(R.id.btnUpdate)

        id = intent.getStringExtra("id").toString()
        name = intent.getStringExtra("name").toString()
        empID = intent.getIntExtra("empID",0).toString().toInt()
        pos = intent.getStringExtra("position").toString()
        dob = intent.getStringExtra("dob").toString()
        salary = intent.getIntExtra("salary",0).toString().toInt()
        phone = intent.getLongExtra("phone",0).toString().toLong()
        email = intent.getStringExtra("email").toString()
        address = intent.getStringExtra("address").toString()

        edtEditName.setText(name)
        edtEditID.setText(""+empID)
        edtEditPosition.setText(pos)
        edtEditDate.setText(dob)
        edtEditSalary.setText(""+salary)
        edtEditPhone.setText(""+phone)
        edtEditEmail.setText(email)
        edtEditAddress.setText(address)

        btnUpdate.setOnClickListener {
            val empEdit = mutableMapOf<String,Any>()

            name = edtEditName.text.toString()
            empID = edtEditID.text.toString().toInt()
            pos = edtEditPosition.text.toString()
            dob = edtEditDate.text.toString()
            salary = edtEditSalary.text.toString().toInt()
            phone = edtEditPhone.text.toString().toLong()
            email = edtEditEmail.text.toString()
            address = edtEditAddress.text.toString()

            empEdit["name"] = name
            empEdit["empID"] = empID
            empEdit["position"] = pos
            empEdit["dob"] = dob
            empEdit["salary"] = salary
            empEdit["phone"] = phone
            empEdit["email"] = email
            empEdit["address"] = address

            refEmp.child(id).updateChildren(empEdit).addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(this@editEmployee,"Updation Successful",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@editEmployee,EmployeeList::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this@editEmployee,"Updation failed",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}