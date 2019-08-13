package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String userName;
    TextView txtViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle userData = getIntent().getExtras();
        if(userData == null) {
            return;
        }
        userName = "Login as " + userData.getString("userName");
        txtViewUser = (TextView) findViewById(R.id.txtUser);
        txtViewUser.setText(userName);
    }

    public void staffMenu(View view) {
        Intent intent = new Intent(this, staff_menu.class);
        startActivity(intent);
    }
}
