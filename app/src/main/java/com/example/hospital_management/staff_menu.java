package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class staff_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);
    }

    public void addStaff(View view) {
        Intent intent = new Intent(this, staff_add.class);
        startActivity(intent);
    }

    public void viewStaff(View view) {
        Intent intent = new Intent(this, staff_view.class);
        startActivity(intent);
    }

    public void staffUpdate(View view) {
        Intent intent = new Intent(this, Staff_Update.class);
        startActivity(intent);
    }

    public void deleteStaff(View view) {
        Intent intent = new Intent(this, Staff_Update.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent =  new Intent(staff_menu.this, MainActivity.class);
        startActivity(intent);
    }
}
