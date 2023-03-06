package com.sanjith.vitcap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Vitacap2cappageActivity extends AppCompatActivity {
    TextView abc;
    double latitude,longitude;
    String key;
    String attributeValue;
    String attribute1;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitacap2cappage);
        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getValue() instanceof HashMap) {
                    HashMap<String, Object> hashMap = (HashMap<String, Object>) snapshot.getValue();
                    if (hashMap.get("data") != null) {
                        String attributeValue = hashMap.get("data").toString();
                        // Extract the latitude and longitude values from the attributeValue string
                        int latStartIndex = attributeValue.indexOf("latitude=") + 9;
                        int latEndIndex = attributeValue.indexOf(",", latStartIndex);
                         latitude = Double.parseDouble(attributeValue.substring(latStartIndex, latEndIndex));
                        int longStartIndex = attributeValue.indexOf("longitude=") + 10;
                        int longEndIndex = attributeValue.indexOf("}", longStartIndex);
                         longitude = Double.parseDouble(attributeValue.substring(longStartIndex, longEndIndex));

                        Toast.makeText(getApplicationContext(), "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_SHORT).show();
                        abc = (TextView) findViewById(R.id.textView3);
                        abc.setText("Latitude: " + latitude + "\nLongitude: " + longitude);
                        Log.i("app output", latitude + ", " + longitude);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });

    }
    public void acc(View view){
        // Create a Uri object with the location's latitude and longitude
        Uri locationUri = Uri.parse("geo:" + latitude + "," + longitude);

        // Create an intent to open Google Maps with the location
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);

        // Set the package name to "com.google.android.apps.maps" to ensure that Google Maps is used
        mapIntent.setPackage("com.google.android.apps.maps");

        // Launch the intent
        startActivity(mapIntent);
    }
}