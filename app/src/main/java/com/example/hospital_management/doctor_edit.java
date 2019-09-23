package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class doctor_edit extends AppCompatActivity {
    EditText doc_name, doc_age,doc_desig,doc_adrs,doc_phone,doc_ward;
    TextView doc_id;
    Button save,home;
    DatabaseHelper helperdb;
    SQLiteDatabase db;
    String name,desig,adrs,ward,phone,age,id;
    int getID;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit);

        helperdb = new DatabaseHelper(this);

        home = findViewById(R.id.btn_homeEdit);

        save = findViewById(R.id.btn_save);

        doc_id = findViewById(R.id.doc_id);
        doc_age = findViewById(R.id.doctor_age);
        doc_name = findViewById(R.id.doctor_name);
        doc_desig = findViewById(R.id.doctor_desig);
        doc_adrs = findViewById(R.id.doctor_adrs);
        doc_phone = findViewById(R.id.doctor_tp);
        doc_ward = findViewById(R.id.doctor_ward);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        desig = intent.getStringExtra("desig");
        adrs = intent.getStringExtra("adrs");
        phone = intent.getStringExtra("phone");
        ward = intent.getStringExtra("ward");

        getID = Integer.parseInt(id);

        doc_id.setText(id + "");
        doc_name.setText(name);
        doc_age.setText(age);
        doc_desig.setText(desig);
        doc_adrs.setText(adrs);
        doc_phone.setText(phone);
        doc_ward.setText(ward);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean result =  helperdb.UpdateDoctor(getID,doc_name.getText().toString(),
                        doc_age.getText().toString(),doc_desig.getText().toString(),doc_adrs.getText().toString(),
                        doc_phone.getText().toString(),doc_ward.getText().toString());
                finish();

                if(result) {
                    Toast.makeText(doctor_edit.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(doctor_edit.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

                Intent in = new Intent(doctor_edit.this,doctor_viewAll.class);
                startActivity(in);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(doctor_edit.this, Doctor_Main.class);
                startActivity(in);
            }
        });
    }
}
