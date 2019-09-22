package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText userName, userpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.nameT);
        userpwd = findViewById(R.id.passT);

    }

    public void login(View view) {
        String stname = userName.getText().toString();
        String stpass = userpwd.getText().toString();

        if(stname.equals("Admin") && stpass.equals("asd")){
            Intent intent = new Intent(login.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("uname",stname);

            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(stname.equals("") && stpass.equals("")){
            Toast.makeText(getBaseContext(),"Enter Both Username & Password",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(),"Wrong Username & Password",Toast.LENGTH_SHORT).show();
        }

    }

}


