package com.marko_cvijanovic.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.marko_cvijanovic.project.model.data.Car;
import com.marko_cvijanovic.project.model.db.CarDatabase;

public class EditActivity extends AppCompatActivity {

    private EditText etCarName;
    private EditText etCarPrice;
    private EditText etCarLicence;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

         etCarName = findViewById(R.id.car_edittext_edit);
         etCarPrice = findViewById(R.id.price_edittext_edit);
         etCarLicence = findViewById(R.id.licence_edittext_edit);

        id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        double price = getIntent().getDoubleExtra("price", 0);
        String licence = getIntent().getStringExtra("licence");

        if(id == 0) {
            Toast.makeText(this, "Go back. Error with loading intent extras.", Toast.LENGTH_LONG);
            setCancelListener();
        }else{
            etCarName.setText(name);
            etCarPrice.setText(String.valueOf(price));
            etCarLicence.setText(licence);
            setEditListener();
            setCancelListener();
        }

    }


    private void setEditListener(){

        Button btnDone = findViewById(R.id.edit_button_edit);

        btnDone.setOnClickListener(fun1 -> {
            Car c = new Car(id, etCarName.getText().toString(), Double.parseDouble(etCarPrice.getText().toString()), etCarLicence.getText().toString());
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    CarDatabase.getDatabase(EditActivity.this).getDAO().updateCar(c);

                    }
            }.start();
            finish();
        });

    }


    private void setCancelListener(){

        Button btnCancel = findViewById(R.id.cancel_button_edit);

        btnCancel.setOnClickListener(fun2 -> {
            Toast.makeText(this, "aaaa", Toast.LENGTH_LONG);
            finish();
        });


    }

}
