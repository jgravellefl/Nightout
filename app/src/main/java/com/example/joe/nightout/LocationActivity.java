package com.example.joe.nightout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class LocationActivity extends AppCompatActivity {

    String[] eventTypes = {"Party", "Sports", "Live Music", "Free Stuff"};
    EditText lat;
    double latitude;
    double longitude;
    EditText lon;
    MaterialBetterSpinner spinner;
    EditText descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, eventTypes);
        MaterialBetterSpinner betterSpinner = findViewById(R.id.better_spinner);
        betterSpinner.setAdapter(aa);
        init();
    }
    private void init(){
        Button backButton = (Button)findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button enterButton = (Button)findViewById(R.id.button3);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lat = (EditText)findViewById(R.id.latitude);
                String latx = lat.getText().toString();
                    try {
                        latitude = Double.parseDouble(latx);
                    } catch (NumberFormatException e) {
                        System.out.println("not a number");
                    }

                lon = (EditText)findViewById(R.id.longitude);
                String lonx = lon.getText().toString();
                    try {
                        longitude = Double.parseDouble(lonx);
                    } catch (NumberFormatException e) {
                        System.out.println("not a number");
                    }
                    System.out.println(latitude);
                    System.out.println(longitude);
                MaterialBetterSpinner materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.better_spinner);
                String name = materialBetterSpinner.getText().toString();
                Intent data = new Intent();
                descriptions = (EditText)findViewById(R.id.description);
                String lastDescription = descriptions.getText().toString();
                EditText eventTitle = (EditText)findViewById(R.id.namee);
                String eventName = eventTitle.getText().toString();
                data.putExtra("Lat", latitude);
                data.putExtra("Long", longitude);
                data.putExtra("activity", name);
                data.putExtra("description", lastDescription);
                data.putExtra("Name", eventName);
                setResult(RESULT_OK, data);
                finish();

            }
        });


    }


}
