package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class staff_add extends AppCompatActivity {


    Button insert_btn;
    EditText in_name,in_age,in_gender,in_address,in_contact,int_role;
    private DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add);
        in_name=findViewById(R.id.sin_name);
        in_age=findViewById(R.id.sin_gen);
        in_gender=findViewById(R.id.sin_gen);
        in_address=findViewById(R.id.sin_add);
        in_contact=findViewById(R.id.sin_cont);
        int_role=findViewById(R.id.sin_role);


        insert_btn=findViewById(R.id.st_insert);


        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i_name=in_name.getText().toString();
                String i_age=in_age.getText().toString();
                String i_gender=in_gender.getText().toString();
                String i_address=in_address.getText().toString();
                String i_contact=in_contact.getText().toString();
                String i_role=int_role.getText().toString();


                if (i_name.equals("")|| i_age.equals("")|| i_role.equals("")){
                    Toast.makeText(staff_add.this, "Fill",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (dbHelper.addStaffDetail(i_name, i_age, i_gender, i_address, i_contact, i_role)){
                    in_name.setText("");
                    in_age.setText("");
                    in_gender.setText("");
                    in_address.setText("");
                    in_contact.setText("");
                    int_role.setText("");
                    Toast.makeText(staff_add.this,"Inserted Succcesfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(staff_add.this,"Inserted Succcesfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
