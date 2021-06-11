package com.example.android_practice16_room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android_practice16_room_database.room.Employee;
import com.example.android_practice16_room_database.room.EmployeeRoomDb;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private EmployeeRoomDb employeeRoomDb;

    List<Employee> employeeList;
    ListView employeeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeeListView = findViewById(R.id.lv_employees);
        employeeList = new ArrayList<>();

        employeeRoomDb = EmployeeRoomDb.getInstance(this);
        loadEmployees();
    }

    private void loadEmployees() {
        employeeList = employeeRoomDb.employeeDao().getAllEmployees();
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList);
        employeeListView.setAdapter(employeeAdapter);

    }
}