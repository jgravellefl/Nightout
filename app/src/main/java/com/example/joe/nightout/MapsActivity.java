package com.example.joe.nightout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    float color;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }

    private void init() {
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MapsActivity.this, LocationActivity.class), 1);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Gainesville = new LatLng(29.651634, -82.344829);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Gainesville));
        mMap.setMinZoomPreference(15);
        mMap.setMaxZoomPreference(25);
    }

    public void addMarker(Double latitude, Double longitude, String activity,String name, String description){
        color = 240.0f;
        switch (activity) {
            case("Party"):
                color = 0.0f;
                break;
            case ("Live Music"):
                color = 210.0f;
                break;
            case("Sports"):
                color = 120.0f;
                break;
            default:
                color = 240.0f;
        }

        LatLng newPlace = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(newPlace).title(activity).icon(BitmapDescriptorFactory.defaultMarker(color)).snippet(description));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        double latitude = data.getDoubleExtra("Lat", 0);
        double longitude = data.getDoubleExtra("Long", 0);
        String type = data.getStringExtra("activity");
        String description = data.getStringExtra("description");
        String activityName = data.getStringExtra("name");
        addMarker(latitude, longitude, type,activityName, description);
    }
}
