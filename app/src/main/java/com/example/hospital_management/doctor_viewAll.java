package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class doctor_viewAll extends AppCompatActivity {

    DatabaseHelper dbhelper;
    Doctors doctors;
    ArrayList<Doctors> doc_arr;
    doctor_adapter doc_adapter;
    ListView listView;
    SearchView search;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_all);

        listView = findViewById(R.id.listview);
        search = findViewById(R.id.doc_search);

        dbhelper = new DatabaseHelper(this);

        home = findViewById(R.id.btnViewAll_home);

        doc_arr = new ArrayList<>();
        doc_arr = dbhelper.getdoctorInfo();

        doc_adapter = new doctor_adapter(this,dbhelper.getdoctorInfo());

        listView.setAdapter(doc_adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                doc_adapter.getFilter().filter(query);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the position on list items
                doctors =  doc_adapter.getItem(position);
                Intent intent = new Intent(doctor_viewAll.this,doctor_view.class);

                //set key and data from database
                intent.putExtra("id",doctors.getId());
                intent.putExtra("name",doctors.getName());
                intent.putExtra("age",doctors.getAge());
                intent.putExtra("desig",doctors.getDesignation());
                intent.putExtra("adrs",doctors.getAddress());
                intent.putExtra("phone",doctors.getPhone());
                intent.putExtra("ward",doctors.getWard());
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(doctor_viewAll.this, Doctor_Main.class);
                startActivity(in);
            }
        });
    }
}
