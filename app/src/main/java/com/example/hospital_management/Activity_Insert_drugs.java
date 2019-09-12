package com.example.hospital_management;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Insert_drugs extends AppCompatActivity {
    Button in_btn;
    EditText in_et_name,in_et_manufacturer,in_et_quantity,in_et_price,in_et_description;
    private DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__insert_drugs);
        in_et_name=findViewById(R.id.in_name);
        in_et_manufacturer=findViewById(R.id.in_manufacturer);
        in_et_quantity=findViewById(R.id.in_quantity);
        in_et_price=findViewById(R.id.inprice);
        in_et_description=findViewById(R.id.in_description);

        in_btn=findViewById(R.id.ininsert);

        in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_name=in_et_name.getText().toString();
                String et_manufacturer=in_et_manufacturer.getText().toString();
                String et_quantity=in_et_quantity.getText().toString();
                String et_price=in_et_quantity.getText().toString();
                String et_description=in_et_description.getText().toString();


                if(et_name.equals("")|| et_manufacturer.equals("")|| et_quantity.equals("")|| et_price.equals("")){
                    Toast.makeText(Activity_Insert_drugs.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(dbHelper.addDrugsDetail(et_name, et_manufacturer, et_quantity, et_price, et_description)){
                    in_et_name.setText("");
                    in_et_manufacturer.setText("");
                    in_et_quantity.setText("");
                    in_et_price.setText("");
                    in_et_description.setText("");
                    Toast.makeText(Activity_Insert_drugs.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(Activity_Insert_drugs.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

