package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class staff_search extends AppCompatActivity {

    TableLayout table_search;
    SearchView st_search;
    private DatabaseHelper dbHelper;
    private ArrayList<StaffModel> staffModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_search);

        dbHelper = new DatabaseHelper(this);
        String s_name=getIntent().getStringExtra("staff_name");
        staffModelArrayList=dbHelper.searchStaff(s_name);


        st_search=findViewById(R.id.staff_sch2);
        st_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(staff_search.this, staff_search.class);
                intent.putExtra("staff_name", st_search.getQuery().toString());
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        table_search=findViewById(R.id.view_st_table);

        table_search.setStretchAllColumns(true);
        if (staffModelArrayList!=null){
            for (int i= 0;i<staffModelArrayList.size();i++)
            {
                TableRow row = new TableRow(this);

                final String id = Integer.toString(staffModelArrayList.get(i).getId());
                String name = staffModelArrayList.get(i).getName();
                String role = staffModelArrayList.get(i).getRole();


                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(staff_search.this,Staff_Update.class);
                        intent.putExtra("staff_id", id);
                        startActivity(intent);

                    }
                });

                TextView stid=new TextView(this);
                stid.setText("  "+id);
                stid.setTextAppearance(getApplicationContext(),R.style.table_row_tView1);

                TextView stname=new TextView(this);
                stname.setText(""+name);
                stname.setTextAppearance(getApplicationContext(),R.style.table_row_tView2);

                TextView strole=new TextView(this);
                stname.setText(""+role);
                stname.setTextAppearance(getApplicationContext(),R.style.table_row_tView3);


                row.addView(stid);
                row.addView(stname);
                row.addView(strole);
                table_search.addView(row);
            }
        }TableRow row = new TableRow(this);
        TextView stid = new TextView(this);
        //stid.setText("Empty Staff");
        stid.setTextAppearance(getApplicationContext(),R.style.table_row_tView1);
        stid.setGravity(Gravity.CENTER);
        row.addView(stid);
        table_search.addView(row);



    }
}