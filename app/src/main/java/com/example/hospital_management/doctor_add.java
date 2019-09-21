package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class doctor_add extends AppCompatActivity {

    EditText doc_name, doc_age,doc_desig,doc_adrs,doc_tp,doc_ward;
    Button submit, home;
    DatabaseHelper helperdb;
    String name,desig,adrs,ward,tp,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add);

        helperdb = new DatabaseHelper(this);

        home = findViewById(R.id.btnAdd_home);

        doc_name = findViewById(R.id.doctor_name);
        doc_age = findViewById(R.id.doctor_age);
        doc_desig = findViewById(R.id.doctor_desig);
        doc_adrs = findViewById(R.id.doctor_adrs);
        doc_tp = findViewById(R.id.doctor_tp);
        doc_ward = findViewById(R.id.doctor_ward);

        submit = findViewById(R.id.btnsearch);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = doc_name.getText().toString();
                age = doc_age.getText().toString();
                desig = doc_desig.getText().toString();
                adrs = doc_adrs.getText().toString();
                tp = doc_tp.getText().toString();
                ward = doc_ward.getText().toString();

                if(doc_name.length() == 0 &&  doc_age.length() == 0 && doc_adrs.length() == 0 && doc_desig.length() == 0 && doc_tp.length() == 0 && doc_ward.length() == 0) {
                    Toast.makeText(doctor_add.this,"Fields can not be empty!", Toast.LENGTH_SHORT).show();
                }else if(helperdb.TextIsValid(name)==false) {
                    Toast.makeText(doctor_add.this,"Invalid Name!", Toast.LENGTH_SHORT).show();
                }else if(helperdb.NumberIsValid(age)==false){
                    Toast.makeText(doctor_add.this,"Invalid age!", Toast.LENGTH_SHORT).show();
                }else if( helperdb.TextIsValid(desig)==false) {
                    Toast.makeText(doctor_add.this,"Invalid designation!", Toast.LENGTH_SHORT).show();
                }else if(helperdb.MobileNumberIsValid(tp)==false) {
                    Toast.makeText(doctor_add.this,"Invalid Contact Number!", Toast.LENGTH_SHORT).show();
                } else if(helperdb.NumberIsValid(ward)==false ) {
                    Toast.makeText(doctor_add.this,"Invalid Ward Number!", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(name,age,adrs,desig,tp,ward);

                    doc_name.setText("");
                    doc_age.setText("");
                    doc_desig.setText("");
                    doc_adrs.setText("");
                    doc_tp.setText("");
                    doc_ward.setText("");

                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(doctor_add.this, Doctor_Main.class);
                startActivity(in);
            }
        });
    }

    public void insertData(String name,String age,String desig, String adrs, String tp, String ward) {
        boolean insertData = helperdb.addData(name,age,adrs,desig,tp,ward);

        if(insertData == true) {
            Toast.makeText(doctor_add.this,"Successfully Inserted!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(doctor_add.this,"Something went wrong!", Toast.LENGTH_SHORT).show();
    }
}
