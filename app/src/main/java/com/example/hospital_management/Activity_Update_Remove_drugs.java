package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Update_Remove_drugs extends AppCompatActivity {
    TextView up_tv_name,up_tv_Manufacturer,up_tv_quantity,up_tv_price,up_tv_description;
    Button up_up_btn,up_del_btn;
    String id;
    ArrayList<DrugModel> drugArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__update__remove_drugs);

        up_tv_name=findViewById(R.id.up_name);
        up_tv_Manufacturer=findViewById(R.id.up_manufacturer);
        up_tv_quantity=findViewById(R.id.up_quantity);
        up_tv_price=findViewById(R.id.up_price);
        up_tv_description=findViewById(R.id.up_description);

        up_up_btn=findViewById(R.id.up_update_btn);
        up_del_btn=findViewById(R.id.up_delete_btn);


        id=getIntent().getStringExtra("drug_id");
        drugArray= dbHelper.getDrug(id);

        final int id=drugArray.get(0).getId();
        String name=drugArray.get(0).getName();
        String manufac=drugArray.get(0).getManufacturer();
        String quantity=drugArray.get(0).getQuantity();
        String price=drugArray.get(0).getPrice();
        String description=drugArray.get(0).getDescription();

        up_tv_name.setText(drugArray.get(0).getName());
        up_tv_Manufacturer.setText(""+manufac);
        up_tv_quantity.setText(""+quantity);
        up_tv_price.setText(""+price);
        up_tv_description.setText(""+description);

        final String new_name=up_tv_name.getText().toString();
        final String new_manufacturer=up_tv_Manufacturer.getText().toString();
        final String new_quantity=up_tv_quantity.getText().toString();
        final String new_price=up_tv_price.getText().toString();
        final String new_description=up_tv_description.getText().toString();

        up_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.updateDrugs(id, up_tv_name.getText().toString(), up_tv_Manufacturer.getText().toString(),up_tv_quantity.getText().toString(),up_tv_price.getText().toString(), up_tv_description.getText().toString())){
                    Toast.makeText(Activity_Update_Remove_drugs.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Activity_Update_Remove_drugs.this,Activity_View_drugs.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Activity_Update_Remove_drugs.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        up_del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(Activity_Update_Remove_drugs.this);
                alert_box.setMessage("Do You Really Want To Delete This Drug?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deleteDrug(id)){
                                    Toast.makeText(Activity_Update_Remove_drugs.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Activity_Update_Remove_drugs.this,Activity_View_drugs.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Activity_Update_Remove_drugs.this,"Delete Faild",Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
                AlertDialog alert = alert_box.create();
                alert.setTitle("Alert !!!");
                alert.show();

            }
        });


    }
}

