package com.example.employeemanagement

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EmpDetails : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance() //tells where is our database located
    val refEmp: DatabaseReference = database.reference.child("EmployeeManagement")//creates a table using function child

    lateinit var txtname: TextView
    lateinit var txtEmpid: TextView
    lateinit var txtPosition: TextView
    lateinit var txtDOB: TextView
    lateinit var txtSalary: TextView
    lateinit var txtPhone: TextView
    lateinit var txtEmail: TextView
    lateinit var txtAddress: TextView

    lateinit var id: String
    lateinit var name: String
    var empID: Int=0
    lateinit var position: String
    lateinit var dob: String
    var salary: Int=0
    var phone: Long=0
    lateinit var email: String
    lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details)
        txtname = findViewById(R.id.txtname)
        txtEmpid = findViewById(R.id.txtid)
        txtPosition = findViewById(R.id.txtPosition)
        txtDOB = findViewById(R.id.txtDOB)
        txtSalary = findViewById(R.id.txtSalary)
        txtPhone = findViewById(R.id.txtPhone)
        txtEmail = findViewById(R.id.txtEmail)
        txtAddress = findViewById(R.id.txtAddress)

        id = intent.getStringExtra("id").toString()
        name = intent.getStringExtra("name").toString()
        empID = intent.getIntExtra("empID",0).toString().toInt()
        position = intent.getStringExtra("position").toString()
        dob = intent.getStringExtra("dob").toString()
        salary = intent.getIntExtra("salary",0).toString().toInt()
        phone = intent.getLongExtra("phone",0).toString().toLong()
        email = intent.getStringExtra("email").toString()
        address = intent.getStringExtra("address").toString()

        txtname.text = name
        txtEmpid.text = empID.toString()
        txtPosition.text = position
        txtDOB.text = dob
        txtSalary.text = salary.toString()
        txtPhone.text = phone.toString()
        txtEmail.text = email
        txtAddress.text = address

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu2,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.edit -> {
                var intent = Intent(this@EmpDetails,editEmployee :: class.java)
                intent.putExtra("id",id)
                intent.putExtra("name",name)
                intent.putExtra("empID",empID)
                intent.putExtra("position",position)
                intent.putExtra("dob",dob)
                intent.putExtra("salary",salary)
                intent.putExtra("phone",phone)
                intent.putExtra("email",email)
                intent.putExtra("address",address)
                startActivity(intent)
            }

            R.id.remove -> {
                var alert = AlertDialog.Builder(this@EmpDetails)
                alert.setTitle("Remove Employee?")
                alert.setMessage("Do you want to remove ${name}'s details?")
                alert.setCancelable(false)
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    refEmp.child(id).removeValue().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //var intent = Intent(this@EmpDetails,EmployeeList :: class.java )
                            //startActivity(intent)
                            Toast.makeText(this@EmpDetails, "${name} got deleted!", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(this@EmpDetails, "Deletion failed, try again!", Toast.LENGTH_SHORT).show()
                        }
                        var intent = Intent(this@EmpDetails, EmployeeList::class.java)
                        startActivity(intent)
                    }
                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                alert.create().show()
            }

            R.id.home -> {
                var intent = Intent(this@EmpDetails , MainActivity :: class.java)
                startActivity(intent)
            }
            android.R.id.home ->{
                onBackPressed()
            }
        }

        return true
    }
}