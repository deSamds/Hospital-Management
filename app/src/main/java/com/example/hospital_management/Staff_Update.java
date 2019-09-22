
package com.example.hospital_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Staff_Update extends AppCompatActivity {

    TextView up_st_name,up_st_age,up_st_gender,up_st_add,up_st_contact,up_st_role;
    Button up_st_btn,de_st_btn;
    String id;
    ArrayList<StaffModel> staffArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__update);

        up_st_name=findViewById(R.id.sup_name);
        up_st_age=findViewById(R.id.sup_age);
        up_st_gender=findViewById(R.id.sup_gender);
        up_st_add=findViewById(R.id.sup_add);
        up_st_contact=findViewById(R.id.sup_cont);
        up_st_role=findViewById(R.id.sup_role);

        up_st_btn=findViewById(R.id.upd_st_btn);
        de_st_btn=findViewById(R.id.del_st_btn);


        id=getIntent().getStringExtra("staff_id");
        staffArray = dbHelper.getStaff(id);

        final int id=staffArray.get(0).getId();
        String name=staffArray.get(0).getName();
        String age=staffArray.get(0).getAge();
        String gender=staffArray.get(0).getGender();
        String address=staffArray.get(0).getAddress();
        String contact=staffArray.get(0).getConactno();
        String role=staffArray.get(0).getRole();


        up_st_name.setText(staffArray.get(0).getName());
        up_st_age.setText(""+age);
        up_st_gender.setText(""+gender);
        up_st_add.setText(""+address);
        up_st_contact.setText(""+contact);
        up_st_role.setText(""+role);


        final String new_name=up_st_name.getText().toString();
        final String new_age=up_st_age.getText().toString();
        final String new_gender=up_st_gender.getText().toString();
        final String new_address=up_st_add.getText().toString();
        final String new_contact=up_st_contact.getText().toString();
        final String new_role=up_st_role.getText().toString();


        up_st_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.updateStaff(id, up_st_name.getText().toString(), up_st_age.getText().toString(), up_st_gender.getText().toString(), up_st_add.getText().toString(), up_st_contact.getText().toString(), up_st_role.getText().toString())){
                    Toast.makeText(Staff_Update.this,"Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Staff_Update.this,staff_view.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Staff_Update.this,"Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        de_st_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(Staff_Update.this);
                alert_box.setMessage("want to delete staff?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deleteStaff(id)){
                                    Toast.makeText(Staff_Update.this,"Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(Staff_Update.this,staff_view.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Staff_Update.this, "Delete Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog alert = alert_box.create();
                alert.setTitle("Alert");
                alert.show();
            }
        });


    }
}
