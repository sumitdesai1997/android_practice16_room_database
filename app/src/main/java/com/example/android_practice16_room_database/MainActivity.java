package com.example.android_practice16_room_database;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android_practice16_room_database.room.Employee;
import com.example.android_practice16_room_database.room.EmployeeRoomDb;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etSalary;
    Spinner spinnerDept;

    public static final String DATABASE_NAME = "employee_database";

    private EmployeeRoomDb employeeRoomDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etSalary = findViewById(R.id.et_salary);
        spinnerDept = findViewById(R.id.spinner_dept);

        findViewById(R.id.btn_add_employee).setOnClickListener(this);
        findViewById(R.id.tv_display_employees).setOnClickListener(this);

        employeeRoomDb = EmployeeRoomDb.getInstance(this);
    }

    /*private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(20) NOT NULL, " +
                "department VARCHAR(20) NOT NULL, " +
                "joining_date DATETIME NOT NULL, " +
                "salary DOUBLE NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_employee:
                addEmployee();
                break;
            case R.id.tv_display_employees:
                // navigate to Employees activity
                startActivity(new Intent(this, EmployeeActivity.class));
                break;
        }
    }

    private void addEmployee() {
        String name = etName.getText().toString().trim();
        String salary = etSalary.getText().toString().trim();
        String department = spinnerDept.getSelectedItem().toString();

        // getting the current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(calendar.getTime());

        if (name.isEmpty()) {
            etName.setError("name field cannot be empty");
            etName.requestFocus();
            return;
        }

        if (salary.isEmpty()) {
            etName.setError("salary field cannot be empty");
            etName.requestFocus();
            return;
        }

        Employee employee = new Employee(name, department, joiningDate, Double.parseDouble(salary));
        employeeRoomDb.employeeDao().insertEmployee(employee);

        clearFields();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        clearFields();
    }

    private void clearFields() {
        etName.setText("");
        etSalary.setText("");
        spinnerDept.setSelection(0);
        etName.clearFocus();
        etSalary.clearFocus();
    }
}