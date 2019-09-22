package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class staff_view extends AppCompatActivity {


    TableLayout st_table;
    SearchView st_search;
    private DatabaseHelper dbHel;
    private ArrayList<StaffModel> staffModelArrayList;

    @SuppressLint("NewSt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_view);

        dbHel = new DatabaseHelper(this);
        staffModelArrayList = dbHel.getAllStaff();


        st_search = findViewById(R.id.staff_sch);
        st_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(staff_view.this,staff_search.class);
                intent.putExtra("staff_name",st_search.getQuery().toString());
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        st_table = findViewById(R.id.view_st_table);
        st_table.setStretchAllColumns(true);
        if (staffModelArrayList != null) {
            for (int i = 0; i < staffModelArrayList.size(); i++) {
                TableRow row = new TableRow(this);
                // row.setBackgroundColor(Color.parseColor("FFFFFF"));
                final String id = Integer.toString(staffModelArrayList.get(i).getId());
                String name = staffModelArrayList.get(i).getName();
                String role = staffModelArrayList.get(i).getRole();


                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(staff_view.this, Staff_Update.class);
                        intent.putExtra("staff_id", id);
                        startActivity(intent);
                    }
                });

                TextView viewid = new TextView(this);
                viewid.setText("  " + id);
                viewid.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);

                TextView viewname = new TextView(this);
                viewname.setText("" + name);
                viewname.setTextAppearance(getApplicationContext(), R.style.table_row_tView2);

                TextView viewrole = new TextView(this);
                viewrole.setText("" + role);
                viewrole.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);


                row.setBottom(2);

                row.addView(viewid);
                row.addView(viewname);
                row.addView(viewrole);
                st_table.addView(row);

            }
        } else {
            TableRow msg = new TableRow(this);
//            msg.setBackgroundColor(Color.parseColor("FFFFFF"));
            TextView viewmsg = new TextView(this);
            viewmsg.setText("Empty Staff");
            viewmsg.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
            viewmsg.setGravity(Gravity.CENTER);
            msg.addView(viewmsg);
            st_table.addView(msg);


        }


    }

}