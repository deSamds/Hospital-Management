package com.example.hospital_management;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class Activity_View_drugs extends AppCompatActivity {

    TableLayout table_tb;
    SearchView sv_search;
    private DatabaseHelper dbHelper;
    private ArrayList<DrugModel> drugModelArrylist;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_drugs);
        dbHelper=new DatabaseHelper(this);
        drugModelArrylist=dbHelper.getAllDrugs();

        sv_search=findViewById(R.id.view_drug_search1);
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(Activity_View_drugs.this,Activity_Search_drug.class);
                intent.putExtra("drug_name",sv_search.getQuery().toString());
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });




        table_tb=findViewById(R.id.view_table);
        //TableLayout table = (TableLayout) findViewById(R.id.view_table);
        table_tb.setStretchAllColumns(true);
        if(drugModelArrylist!=null) {
            for (int i = 0; i < drugModelArrylist.size(); i++) {


                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
                final String id = Integer.toString(drugModelArrylist.get(i).getId());
                String name = drugModelArrylist.get(i).getName();
                String manufac = drugModelArrylist.get(i).getManufacturer();
                String quantity = drugModelArrylist.get(i).getQuantity();

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Activity_View_drugs.this, Activity_Update_Remove_drugs.class);
                        intent.putExtra("drug_id", id);
                        startActivity(intent);
                    }
                });

                TextView tvid = new TextView(this);
                tvid.setText("    " + id);
                tvid.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
                TextView tvname = new TextView(this);
                tvname.setText("" + name);
                tvname.setTextAppearance(getApplicationContext(), R.style.table_row_tView2);
                TextView tvamount = new TextView(this);
                tvamount.setText("" + quantity);
                tvamount.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);



                row.setBottom(2);

                row.addView(tvid);
                row.addView(tvname);
                row.addView(tvamount);
                table_tb.addView(row);
            }
        }else{
            TableRow rowMsg = new TableRow(this);
            rowMsg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tvmsg = new TextView(this);
            tvmsg.setText("No Drugs");
            tvmsg.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
            tvmsg.setGravity(Gravity.CENTER);
            rowMsg.addView(tvmsg);
            table_tb.addView(rowMsg);
        }
    }
}

