package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Hospital_management extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_management);

        ImageView image = (ImageView)findViewById(R.id.hospital_logo);
        TextView text = (TextView) findViewById(R.id.hospital_name);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade);
        image.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        text.startAnimation(animation2);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        }, 3000);
    }

}
