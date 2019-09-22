package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String userName;
    Button docBtn, drugBtn, patientBtn, staffBtn;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle userData = getIntent().getExtras();
        if(userData == null) {
            return;
        }

        docBtn = findViewById(R.id.btn_doc);
        drugBtn = findViewById(R.id.btn_drug);
        patientBtn = findViewById(R.id.btn_patient);
        staffBtn = findViewById(R.id.btn_staff);
        logout = findViewById(R.id.logout);


        docBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Doctor_Main.class);
                startActivity(intent1);
            }
        });

        drugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, Activity_Drug_main.class);
                startActivity(intent2);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(MainActivity.this, login.class);
                startActivity(logout);
            }
        });
    }

}
