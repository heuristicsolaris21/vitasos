package com.sanjith.vitasos;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class usersosscreen extends AppCompatActivity implements LocationListener  {

    public static String number;
    LocationManager locationManager;
    Double latitude, longitude;
    Button sos;
    TextView loc,phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersosscreen);
        sos=findViewById(R.id.button2);
        loc=findViewById(R.id.textView4);
        phoneno=findViewById(R.id.textView5);
        Intent s=getIntent();
        String phonetxt =s.getStringExtra(number);
        phoneno.setText(phonetxt);
        if (ContextCompat.checkSelfPermission(usersosscreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(usersosscreen.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        sos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(usersosscreen.this, "SOS pressed", Toast.LENGTH_SHORT).show();
                getLocation();
            }
        });
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {
        loc.setText("...");
        try {

            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 5F,usersosscreen.this);
            loc.setText("Getting coordinates....");
            //loc.setText(""+location.getLatitude()+","+location.getLongitude());
        }catch (Exception e){
            loc.setText("***");
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        //loc.setText(""+location.getLatitude()+","+location.getLongitude());
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        loc.setText(latitude+","+longitude);
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(usersosscreen.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            //loc.setText(""+location.getLatitude()+","+location.getLongitude());

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}