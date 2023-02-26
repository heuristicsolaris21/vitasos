package com.sanjith.vitcap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    String key;
    String attributeValue;
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
                        Toast.makeText(getApplicationContext(), attributeValue, Toast.LENGTH_SHORT).show();
                        abc=(TextView)findViewById(R.id.textView3);
                        abc.setText(attributeValue);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });

        abc=(TextView)findViewById(R.id.textView3);
        //Toast.makeText(this, "asdf"+attributeValue, Toast.LENGTH_SHORT).show();
        //abc.setText(attributeValue);


    }
}