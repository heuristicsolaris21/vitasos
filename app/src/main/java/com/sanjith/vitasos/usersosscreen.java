package com.sanjith.vitasos;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
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
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class usersosscreen extends AppCompatActivity implements LocationListener  {
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    public static String number;
    String phone;
    String message;
    LocationManager locationManager;
    Double latitude, longitude;
    Button sos;
    TextView loc,phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //public ==sanjith
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersosscreen);
        sos=findViewById(R.id.button2);
        loc=findViewById(R.id.textView4);
        phoneno=findViewById(R.id.textView5);
        Intent s=getIntent();
        String phonetxt =s.getStringExtra(number);
        phoneno.setText(phonetxt);
        databasereference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String emerphone=snapshot.child(phonetxt).child("editemer").getValue(String.class);
                Log.i("testing","sms"+emerphone);
                phone = "+91"+emerphone;
                loc.setText(phone);
                Toast.makeText(usersosscreen.this, "posted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(usersosscreen.this,"sms function called",Toast.LENGTH_SHORT).show();
                sendSMSMessage();
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


    protected void sendSMSMessage() {
        /*databasereference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String emerphone=snapshot.child(phonetxt).child("editemer").getValue(String.class);
                Log.i("testing","sms"+emerphone);
                phone = "+91"+emerphone;
                loc.setText(phone);
                Toast.makeText(usersosscreen.this, "posted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        message = "this is sent besause of pressing SOS";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}