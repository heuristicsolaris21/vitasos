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

public class Vitacap2cappageActivity extends AppCompatActivity {
    TextView abc;
    String key;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/").child("data");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitacap2cappage);

        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("data");
        Query queryUid=databasereference.orderByKey().limitToFirst(1);
        queryUid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    key=datas.getKey();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        abc=(TextView)findViewById(R.id.textView3);
        abc.setText(key);


    }
}