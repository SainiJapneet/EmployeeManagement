package com.example.employeemanagement

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.core.view.get
import com.google.firebase.database.*

class EmployeeList : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance() //tells where is our database located
    val refEmp: DatabaseReference = database.reference.child("EmployeeManagement")//creates a table using function child

    var arrList= ArrayList<Model>()
    lateinit var lstView: ListView

    lateinit var name: String
    var ID: Int= 0
    var salary: Int= 0
    var phone: Long= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        lstView = findViewById(R.id.listView)

        refEmp.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrList.clear()

                for(eachEmp in snapshot.children){
                    val emp = eachEmp.getValue(Model :: class.java)
                    if (emp != null) {
                        arrList.add(emp)
                    }
                }
                lstView.adapter = arrAdapter(this@EmployeeList,R.layout.row, arrList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        lstView.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this@EmployeeList,EmpDetails:: class.java)
            intent.putExtra("id",arrList[position].id)
            intent.putExtra("name",arrList[position].name)
            intent.putExtra("empID",arrList[position].empID)
            intent.putExtra("position",arrList[position].position)
            intent.putExtra("dob",arrList[position].dob)
            intent.putExtra("salary",arrList[position].salary)
            intent.putExtra("phone",arrList[position].phone)
            intent.putExtra("email",arrList[position].email)
            intent.putExtra("address",arrList[position].address)
            startActivity(intent)
        }
    }
}