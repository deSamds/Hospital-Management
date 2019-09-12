package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Drug_main extends AppCompatActivity {
    private Button insert,view,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__drug_main);

        insert = (Button) findViewById(R.id.insert_btn);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Drug_main.this, Activity_Insert_drugs.class);
                startActivity(intent);
            }
        });
        view = (Button) findViewById(R.id.view_btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Drug_main.this, Activity_View_drugs.class);
                startActivity(intent);
            }
        });
        edit = (Button) findViewById(R.id.toHome_btn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Drug_main.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

