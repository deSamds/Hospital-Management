package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText userName;
    String strUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.nameT);
}

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        strUser = userName.getText().toString();
        intent.putExtra("userName", strUser);
        startActivity(intent);
    }
}
