package com.example.hospital_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class doctor_view extends AppCompatActivity {

    Button edit, delete,home;
    SQLiteDatabase db;
    DatabaseHelper helper;
    TextView dname, age, desig, adrs, phone, ward, id;
    String doc_name, doc_age, doc_desig, doc_adrs, doc_phone, doc_ward, doc_id;
    int getID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);

        home = findViewById(R.id.btnView_home);
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btnMain_home);

        id = findViewById(R.id.doctor_id);
        dname = findViewById(R.id.doctor_name);
        age = findViewById(R.id.doctor_age);
        desig = findViewById(R.id.doctor_desig);
        adrs = findViewById(R.id.doctor_adrs);
        phone = findViewById(R.id.doctor_tp);
        ward = findViewById(R.id.doctor_ward);

        helper = new DatabaseHelper(this);

        //Get intent from View All
        Intent intent = getIntent();
        if (intent != null) {

            doc_id = intent.getStringExtra("id");
            doc_name = intent.getStringExtra("name");
            doc_age = intent.getStringExtra("age");
            doc_desig = intent.getStringExtra("desig");
            doc_adrs = intent.getStringExtra("adrs");
            doc_phone = intent.getStringExtra("phone");
            doc_ward = intent.getStringExtra("ward");
        }

        getID = Integer.parseInt(doc_id);

        id.setText(getID + "");
        dname.setText(doc_name);
        age.setText(doc_age);
        desig.setText(doc_desig);
        adrs.setText(doc_adrs);
        phone.setText(doc_phone);
        ward.setText(doc_ward);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(doctor_view.this, doctor_edit.class);

                in.putExtra("id", doc_id);
                in.putExtra("name", doc_name);
                in.putExtra("age", doc_age);
                in.putExtra("desig", doc_desig);
                in.putExtra("adrs", doc_adrs);
                in.putExtra("phone", doc_phone);
                in.putExtra("ward", doc_ward);

                startActivity(in);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = helper.getWritableDatabase();

                AlertDialog alertDialog = deleteConfirm();
                alertDialog.show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(doctor_view.this, Doctor_Main.class);
                startActivity(in);
            }
        });

    }
    private AlertDialog deleteConfirm () {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("Delete").setMessage("Do you want to Delete?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean result = helper.DeleteDoctor(getID);
                finish();
                dialogInterface.dismiss();

                if(result == true) {
                    Toast.makeText(doctor_view.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(doctor_view.this,doctor_viewAll.class);
                    startActivity(intent);
                }else
                    Toast.makeText(doctor_view.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        return alertDialog;
    }
}
